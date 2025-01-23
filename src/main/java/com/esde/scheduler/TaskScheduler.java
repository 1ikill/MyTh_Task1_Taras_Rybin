package com.esde.scheduler;

import com.esde.task.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class TaskScheduler {
    private static final Logger logger = LogManager.getLogger();

    private final LinkedBlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();
    private final List<Thread> workers = new ArrayList<>();
    private AtomicBoolean isRunning = new AtomicBoolean(true);

    public TaskScheduler(int threadPoolSize) {
        for (int i = 0; i < threadPoolSize; i++) {
            Thread worker = new Thread(() -> {
                long pollTimeout = 1;
                while (isRunning.get() || !taskQueue.isEmpty()) {
                    try {
                        Task task = taskQueue.poll(pollTimeout, TimeUnit.SECONDS);
                        if (task != null) {
                            task.run();
                            pollTimeout = 0;
                        } else {
                            pollTimeout = 1;
                        }
                    } catch (InterruptedException e) {
                        if (!isRunning.get()) {
                            logger.info("Worker interrupted during shutdown. Exiting thread.");
                            Thread.currentThread().interrupt();
                            return;
                        } else {
                            logger.error("Worker interrupted unexpectedly.", e);
                            throw new RuntimeException(e);
                        }
                    }
                }
                logger.info("Worker stopped.");
            });
            workers.add(worker);
            worker.start();
        }
    }

    public void scheduleTask(Task task, long delay, TimeUnit unit) {
        new Thread(() -> {
            try {
                unit.sleep(delay);
                taskQueue.offer(task);
                logger.info("Scheduled: " + task.getTaskName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Task scheduling interrupted for: " + task.getTaskName(), e);
            }
        }).start();
    }

    public void stopScheduler() {
        if (isRunning.compareAndSet(true, false)) {
            logger.info("Stopping scheduler.");
            workers.forEach(Thread::interrupt);
        }
    }
}

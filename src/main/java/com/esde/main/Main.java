package com.esde.main;

import com.esde.creator.TaskFactory;
import com.esde.creator.impl.TaskFactoryImpl;
import com.esde.scheduler.TaskScheduler;
import com.esde.task.Task;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        TaskScheduler scheduler = new TaskScheduler(5);
        TaskFactory taskFactory = new TaskFactoryImpl();
        List<Task> tasks = taskFactory.createTasksFromFile("/tasks.txt");

//        scheduler.scheduleTask(taskFactory.createTask(TaskType.CACHE_REFRESH), 2, TimeUnit.SECONDS);
//        scheduler.scheduleTask(taskFactory.createTask(TaskType.VIRUS_SCAN), 1, TimeUnit.SECONDS);

        scheduler.scheduleTask(tasks.get(0), 2, TimeUnit.SECONDS);
        scheduler.scheduleTask(tasks.get(1), 1, TimeUnit.SECONDS);
        scheduler.scheduleTask(tasks.get(2), 3, TimeUnit.SECONDS);
        scheduler.scheduleTask(tasks.get(3), 4, TimeUnit.SECONDS);

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        scheduler.stopScheduler();
    }
}

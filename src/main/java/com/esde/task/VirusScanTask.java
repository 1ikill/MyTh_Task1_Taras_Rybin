package com.esde.task;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class VirusScanTask implements Task {
    private static final Logger logger = LogManager.getLogger();
    private static final TaskType TASK_TYPE = TaskType.VIRUS_SCAN;

    @Override
    public String getTaskName() {
        return TASK_TYPE.getTypeName();
    }

    @Override
    public void run() {
        try {
            logger.info(getTaskName() + " is starting.");
            TimeUnit.SECONDS.sleep(3);
            logger.info(getTaskName() + " is completed.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error(getTaskName() + "was interrupted", e);
            throw new RuntimeException(e);
        }
    }
}
package com.esde.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.TimeUnit;

public class CacheRefreshTask implements Task {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String getTaskName() {
        return "Cache Refresh Task";
    }

    @Override
    public Void call() throws Exception {
        logger.info(getTaskName() + " is starting.");
        TimeUnit.SECONDS.sleep(3);
        logger.info(getTaskName() + " is completed.");
        return null;
    }
}

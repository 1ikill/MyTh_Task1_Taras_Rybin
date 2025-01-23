package com.esde.creator.impl;

import com.esde.creator.TaskFactory;
import com.esde.task.CacheRefreshTask;
import com.esde.task.SoftwareUpdateCheckTask;
import com.esde.task.SystemHealthCheckTask;
import com.esde.task.Task;
import com.esde.task.VirusScanTask;
import com.esde.task.TaskType;
import com.esde.reader.TxtParser;

import java.util.ArrayList;
import java.util.List;

public class TaskFactoryImpl implements TaskFactory {

    @Override
    public List<Task> createTasksFromFile(String file) {
        List<String> data = TxtParser.loadConfig(file);
        List<Task> tasks = new ArrayList<>();
        for (String line : data) {
            tasks.add(createTask(TaskType.fromString(line)));
        }
        return tasks;

    }

    @Override
    public Task createTask(TaskType taskType) {
        return switch (taskType) {
            case SOFTWARE_UPDATE_CHECK -> new SoftwareUpdateCheckTask();
            case SYSTEM_HEALTH -> new SystemHealthCheckTask();
            case VIRUS_SCAN -> new VirusScanTask();
            case CACHE_REFRESH -> new CacheRefreshTask();
        };
    }
}

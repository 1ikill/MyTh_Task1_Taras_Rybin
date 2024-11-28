package com.esde.creator;

import com.esde.task.Task;
import com.esde.task.type.TaskType;

import java.util.List;

public interface TaskFactory {
    List<Task> createTasksFromFile(String file);
    Task createTask(TaskType taskType);
}

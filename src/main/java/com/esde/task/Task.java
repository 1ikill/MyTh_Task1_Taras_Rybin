package com.esde.task;

import java.util.concurrent.Callable;

public interface Task extends Callable<Void> {
    String getTaskName();
}

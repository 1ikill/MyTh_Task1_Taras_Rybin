package com.esde.task;

public enum TaskType {

    SOFTWARE_UPDATE_CHECK("Software Update Check"),
    VIRUS_SCAN("Virus Scan"),
    SYSTEM_HEALTH("System Health Check"),
    CACHE_REFRESH("Cache Refresh");

    private final String typeName;

    TaskType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static TaskType fromString(String typeName) {
        for (TaskType type : TaskType.values()) {
            if (type.typeName.equalsIgnoreCase(typeName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown task type: " + typeName);
    }
}


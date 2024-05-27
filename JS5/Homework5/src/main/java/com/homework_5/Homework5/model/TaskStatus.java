package com.homework_5.Homework5.model;

public enum TaskStatus {
    NOT_STARTED,
    IN_PROGRESS,
    COMPLETED;

    public static TaskStatus fromString(String status) {
        for (TaskStatus s : TaskStatus.values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        throw new IllegalArgumentException("неверный статус: " + status);
    }
}
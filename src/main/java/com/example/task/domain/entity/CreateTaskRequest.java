package com.example.task.domain.entity;

import java.time.LocalDate;

public record CreateTaskRequest(
        String Title,
        String description,
        LocalDate dueDate,
        TaskPriority priority
) {
}

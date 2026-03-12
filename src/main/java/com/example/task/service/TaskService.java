package com.example.task.service;

import com.example.task.domain.entity.CreateTaskRequest;
import com.example.task.domain.entity.Task;
import com.example.task.domain.entity.UpdateTaskRequest;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    public Task createTask(CreateTaskRequest request);
    public List<Task> listTasks();
    public Task updateTask(UUID taskId, UpdateTaskRequest request);
    public void deleteTask(UUID taskId);
}

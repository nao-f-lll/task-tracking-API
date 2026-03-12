package com.example.task.service.impl;

import com.example.task.domain.entity.CreateTaskRequest;
import com.example.task.domain.entity.Task;
import com.example.task.domain.entity.TaskStatus;
import com.example.task.domain.entity.UpdateTaskRequest;
import com.example.task.exception.TaskNotFoundException;
import com.example.task.repository.TaskRepository;
import com.example.task.service.TaskService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;

    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public Task createTask(CreateTaskRequest request) {
        Instant now = Instant.now();

        Task newTask = new Task(
                null,
                request.Title(),
                request.description(),
                request.dueDate(),
                TaskStatus.OPEN,
                request.priority(),
                now,
                now
        );
        return repository.save(newTask);
    }

    @Override
    public List<Task> listTasks() {
        return repository.findAll(Sort.by(Sort.Direction.ASC, "created"));
    }

    @Override
    public Task updateTask(UUID taskId, UpdateTaskRequest request) {

        Task existingTask = repository.findById(taskId).orElseThrow(() ->
                new TaskNotFoundException(taskId)
        );

        existingTask.setTitle(request.title());
        existingTask.setDescription(request.description());
        existingTask.setDueDate(request.dueDate());
        existingTask.setPriority(request.priority());
        existingTask.setStatus(request.status());

        Instant now = Instant.now();

        existingTask.setUpdated(now);

        return repository.save(existingTask);
    }

    @Override
    public void deleteTask(UUID taskId) {
        repository.deleteById(taskId);
    }

}

package com.example.task.controller;

import com.example.task.domain.dto.CreateTaskRequestDto;
import com.example.task.domain.dto.TaskDto;
import com.example.task.domain.dto.UpdateTaskRequestDto;
import com.example.task.domain.entity.CreateTaskRequest;
import com.example.task.domain.entity.Task;
import com.example.task.domain.entity.UpdateTaskRequest;
import com.example.task.mapper.TaskMapper;
import com.example.task.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity<TaskDto> createTask(@Valid @RequestBody CreateTaskRequestDto request) {

        CreateTaskRequest taskToCreate =  taskMapper.formDto(request);
        Task createdTask = taskService.createTask(taskToCreate);
        TaskDto createdTaskDto = taskMapper.toDto(createdTask);
        return new ResponseEntity<>(createdTaskDto, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> listTasks() {

        List<Task> tasksEntities = taskService.listTasks();

        List<TaskDto> tasksDto = Optional.ofNullable(tasksEntities).map(tasks -> tasks.stream()
                .map(taskMapper::toDto)
                .toList())
                .orElse(null);

        return ResponseEntity.ok(tasksDto);

    }

    @PutMapping(path = "/{taskID}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("taskID") UUID taskId, @Valid @RequestBody UpdateTaskRequestDto request) {

        UpdateTaskRequest taskToUpdate = taskMapper.formDto(request);
        Task updatedTask = taskService.updateTask(taskId, taskToUpdate);
        TaskDto updatedTAskDto = taskMapper.toDto(updatedTask);
        return  ResponseEntity.ok(updatedTAskDto);
    }

    @DeleteMapping(path = "/{taskID}") ResponseEntity<Void> deleteTask(@PathVariable UUID taskID) {
        taskService.deleteTask(taskID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
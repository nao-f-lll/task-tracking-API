package com.example.task.mapper;

import com.example.task.domain.dto.CreateTaskRequestDto;
import com.example.task.domain.dto.TaskDto;
import com.example.task.domain.dto.UpdateTaskRequestDto;
import com.example.task.domain.entity.CreateTaskRequest;
import com.example.task.domain.entity.Task;
import com.example.task.domain.entity.UpdateTaskRequest;

public interface TaskMapper {

    CreateTaskRequest formDto(CreateTaskRequestDto dto);
    UpdateTaskRequest formDto(UpdateTaskRequestDto dto);
    TaskDto toDto(Task task);
}

package com.example.task.mapper.iml;

import com.example.task.domain.dto.CreateTaskRequestDto;
import com.example.task.domain.dto.TaskDto;
import com.example.task.domain.dto.UpdateTaskRequestDto;
import com.example.task.domain.entity.CreateTaskRequest;
import com.example.task.domain.entity.Task;
import com.example.task.domain.entity.UpdateTaskRequest;
import com.example.task.mapper.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public CreateTaskRequest formDto(CreateTaskRequestDto dto) {
        return new CreateTaskRequest(
                dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.priority()
        );
    }

    @Override
    public UpdateTaskRequest formDto(UpdateTaskRequestDto dto) {

        return new UpdateTaskRequest(
                dto.title(),
                dto.description(),
                dto.dueDate(),
                dto.priority(),
                dto.status()
        );
    }

    @Override
    public TaskDto toDto(Task task) {
        if (null == task) {
            return null;
        }
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
        );
    }
}

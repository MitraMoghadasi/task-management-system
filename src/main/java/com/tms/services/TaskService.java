package com.tms.services;

import com.tms.models.dto.CreateOrUpdateTaskDto;
import com.tms.models.dto.GetTaskDto;
import com.tms.models.entity.TaskEntity;
import com.tms.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public void createTask(CreateOrUpdateTaskDto taskDto) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(taskDto.getTitle());
        taskEntity.setDescription(taskDto.getDescription());
        taskEntity.setStatus(taskDto.getStatus());
        taskRepository.save(taskEntity);
    }

    public GetTaskDto getTaskById(Long id) {
        Optional<TaskEntity> taskEntityOptional = taskRepository.findById(id);
        TaskEntity taskEntity = taskEntityOptional.get();
        GetTaskDto taskDto = new GetTaskDto();
        taskDto.setId(taskEntity.getId());
        taskDto.setTitle(taskEntity.getTitle());
        taskDto.setDescription(taskEntity.getDescription());
        taskDto.setStatus(taskEntity.getStatus());
        taskDto.setCreatedAt(taskEntity.getCreatedAt());

        return taskDto;
    }

    public void updateTask(CreateOrUpdateTaskDto taskDto, Long id) {
        Optional<TaskEntity> taskEntityOptional = taskRepository.findById(id);
        if(taskEntityOptional.isPresent()) {
            TaskEntity taskEntity = taskEntityOptional.get();
            taskEntity.setTitle(taskDto.getTitle());
            taskEntity.setDescription(taskDto.getDescription());
            taskEntity.setStatus(taskDto.getStatus());
            taskRepository.save(taskEntity);
        }

    }

    public void deleteTask(Long id) {
        Optional<TaskEntity> taskEntityOptional = taskRepository.findById(id);
        if(taskEntityOptional.isPresent()) {
            taskRepository.delete(taskEntityOptional.get());
        }
    }

    public List<GetTaskDto> getTasksList() {
        List<TaskEntity> taskList= taskRepository.findAll();
        List<GetTaskDto> taskDtoList = new ArrayList<>();
        for(int i = 0; i < taskList.size() ; i++){
            TaskEntity taskEntity = taskList.get(i);
            GetTaskDto taskDto = new GetTaskDto();
            taskDto.setId(taskEntity.getId());
            taskDto.setTitle(taskEntity.getTitle());
            taskDto.setDescription(taskEntity.getDescription());
            taskDto.setStatus(taskEntity.getStatus());
            taskDto.setCreatedAt(taskEntity.getCreatedAt());
            taskDtoList.add(taskDto);
        }
        return taskDtoList;
    }

}

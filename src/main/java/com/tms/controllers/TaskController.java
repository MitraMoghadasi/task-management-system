package com.tms.controllers;

import com.tms.models.dto.CreateOrUpdateTaskDto;
import com.tms.models.dto.GetTaskDto;
import com.tms.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tms")

public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping
    public void createTask(@RequestBody CreateOrUpdateTaskDto taskDto) {
        taskService.createTask(taskDto);
    }

    @GetMapping("/{id}")
    public GetTaskDto getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PutMapping("/{id}")
    public void updateTask(@RequestBody CreateOrUpdateTaskDto task, @PathVariable Long id) {
        taskService.updateTask(task , id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping
    public List<GetTaskDto> getTasksList() {
        return taskService.getTasksList();
    }
}

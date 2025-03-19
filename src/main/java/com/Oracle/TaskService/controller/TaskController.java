package com.Oracle.TaskService.controller;

import com.Oracle.TaskService.data.TaskRegister;
import com.Oracle.TaskService.data.TaskResponse;
import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    @PreAuthorize("hasRole('Manager')")
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskRegister taskRequest){
        Task task = taskService.createTask(taskRequest);
        return ResponseEntity.ok(new TaskResponse(
                task.getTask_id(),
                task.getTitle(),
                task.getDescription(),
                task.getEpic_id(),
                task.getPriority(),
                task.getStatus(),
                task.getType(),
                task.getEstimated_deadline(),
                task.getReal_deadline(),
                task.getUser_points()
                ));
    }

}

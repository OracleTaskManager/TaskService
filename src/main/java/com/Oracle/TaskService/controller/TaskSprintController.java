package com.Oracle.TaskService.controller;

import com.Oracle.TaskService.data.TaskSprintRegister;
import com.Oracle.TaskService.service.TaskSprintService;
import jakarta.validation.Valid;
import oracle.ucp.proxy.annotation.Pre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasksprint")
public class TaskSprintController {

    @Autowired
    private TaskSprintService taskSprintService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('Manager')")
    public void addTaskToSprint(@RequestBody @Valid TaskSprintRegister taskSprintRegister){
        try{
            boolean isInSprint = taskSprintService.isTaskInSprint(taskSprintRegister.taskId(), taskSprintRegister.sprintId());
            if(isInSprint){
                System.out.println("Task is already in this sprint");
                return;
            }

            taskSprintService.addTaskToSprint(taskSprintRegister);

        }catch (Exception e){
            System.out.println("Error during task sprint creation: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('Manager')")
    public void removeTaskFromSprint(@RequestBody @Valid TaskSprintRegister taskSprintRegister){
        try{
            boolean isInSprint = taskSprintService.isTaskInSprint(taskSprintRegister.taskId(), taskSprintRegister.sprintId());
            if(!isInSprint){
                System.out.println("Task is not in this sprint");
                return;
            }
            taskSprintService.removeTaskFromSprint(taskSprintRegister);
        }catch (Exception e){
            System.out.println("Error during task sprint deletion: " + e.getMessage());
        }
    }

}

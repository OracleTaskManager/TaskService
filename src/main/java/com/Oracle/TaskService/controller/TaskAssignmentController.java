package com.Oracle.TaskService.controller;

import com.Oracle.TaskService.data.TaskAssignmentRequest;
import com.Oracle.TaskService.service.TaskAssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taskassignments")
public class TaskAssignmentController {

    @Autowired
    private TaskAssignmentService taskAssignmentService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('Manager')")
    public void addTaskAssignment(TaskAssignmentRequest taskAssignmentRequest){
        try{
            boolean isAssigned = taskAssignmentService.isTaskAssigned(taskAssignmentRequest.taskId(), taskAssignmentRequest.userId());
            if(isAssigned){
                System.out.println("User is already assigned to this task");
                return;
            }
            taskAssignmentService.addTaskAssignment(taskAssignmentRequest);
        }catch (Exception e){
            System.out.println("Error during task assignment creation: " + e.getMessage());
        }
    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('Manager')")
    public void removeTaskAssignment(TaskAssignmentRequest taskAssignmentRequest){
        try{
            boolean isAssigned = taskAssignmentService.isTaskAssigned(taskAssignmentRequest.taskId(), taskAssignmentRequest.userId());
            if(!isAssigned){
                System.out.println("User is not assigned to this task");
                return;
            }
            taskAssignmentService.removeTaskAssignment(taskAssignmentRequest);
        }catch (Exception e){
            System.out.println("Error during task assignment deletion: " + e.getMessage());
        }
    }

}

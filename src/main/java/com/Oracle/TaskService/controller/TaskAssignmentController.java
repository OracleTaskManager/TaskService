package com.Oracle.TaskService.controller;

import com.Oracle.TaskService.data.TaskAssignmentRequest;
import com.Oracle.TaskService.service.TaskAssignmentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taskassignments")
public class TaskAssignmentController {

  @Autowired private TaskAssignmentService taskAssignmentService;

  @PostMapping("/add")
  @PreAuthorize("hasRole('Manager')")
  public void addTaskAssignment(@RequestBody @Valid TaskAssignmentRequest taskAssignmentRequest) {
    try {
      boolean isAssigned =
          taskAssignmentService.isTaskAssigned(
              taskAssignmentRequest.taskId(), taskAssignmentRequest.userId());
      if (isAssigned) {
        System.out.println("User is already assigned to this task");
        return;
      }
      taskAssignmentService.addTaskAssignment(taskAssignmentRequest);
    } catch (Exception e) {
      System.out.println("Error during task assignment creation: " + e.getMessage());
    }
  }

  @DeleteMapping("/remove")
  @PreAuthorize("hasRole('Manager')")
  public void removeTaskAssignment(@RequestBody @Valid TaskAssignmentRequest taskAssignmentRequest) {
    try {
      boolean isAssigned =
          taskAssignmentService.isTaskAssigned(
              taskAssignmentRequest.taskId(), taskAssignmentRequest.userId());
      if (!isAssigned) {
        System.out.println("User is not assigned to this task");
        return;
      }
      taskAssignmentService.removeTaskAssignment(taskAssignmentRequest);
    } catch (Exception e) {
      System.out.println("Error during task assignment deletion: " + e.getMessage());
    }
  }

  @GetMapping("/all-Telegram")
  @PreAuthorize("hasRole('Manager')")
  public List<?> getAllTaskAssignmentsTelegram() {
    try {
      return taskAssignmentService.getAllTaskAssignmentsTelegram();
    } catch (Exception e) {
      System.out.println("Error during fetching task assignments for Telegram: " + e.getMessage());
      return null;
    }
  }

  @GetMapping("/")
  @PreAuthorize("hasRole('Manager')")
  public List<?> getAllTaskAssignments() {
    try {
      return taskAssignmentService.getAllTaskAssignments();
    } catch (Exception e) {
      System.out.println("Error during fetching task assignments: " + e.getMessage());
      return null;
    }
  }
}

package com.Oracle.TaskService.controller;

import com.Oracle.TaskService.data.TaskSprintRequest;
import com.Oracle.TaskService.service.TaskSprintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasksprint")
public class TaskSprintController {

  @Autowired private TaskSprintService taskSprintService;

  @PostMapping("/add")
  @PreAuthorize("hasRole('Manager')")
  public void addTaskToSprint(@RequestBody @Valid TaskSprintRequest taskSprintRequest) {
    try {
      boolean isInSprint =
          taskSprintService.isTaskInSprint(
              taskSprintRequest.taskId(), taskSprintRequest.sprintId());
      if (isInSprint) {
        System.out.println("Task is already in this sprint");
        return;
      }

      taskSprintService.addTaskToSprint(taskSprintRequest);

    } catch (Exception e) {
      System.out.println("Error during task sprint creation: " + e.getMessage());
    }
  }

  @DeleteMapping("/remove")
  @PreAuthorize("hasRole('Manager')")
  public void removeTaskFromSprint(@RequestBody @Valid TaskSprintRequest taskSprintRequest) {
    try {
      boolean isInSprint =
          taskSprintService.isTaskInSprint(
              taskSprintRequest.taskId(), taskSprintRequest.sprintId());
      if (!isInSprint) {
        System.out.println("Task is not in this sprint");
        return;
      }
      taskSprintService.removeTaskFromSprint(taskSprintRequest);
    } catch (Exception e) {
      System.out.println("Error during task sprint deletion: " + e.getMessage());
    }
  }
}

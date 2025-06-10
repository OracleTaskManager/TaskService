package com.Oracle.TaskService.controller;

import com.Oracle.TaskService.data.TaskResponse;
import com.Oracle.TaskService.data.TaskSprintRequest;
import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.service.TaskSprintService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

  @GetMapping("/{sprintId}/tasks")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<?> getTasksBySprint(@PathVariable("sprintId") Long sprintId){
    try{
      List<Task> taskList = taskSprintService.getTasksBySprint(sprintId);
      List<TaskResponse> taskResponseList = taskList.stream()
          .map(task -> new TaskResponse(task.getTaskId(), task.getTitle(), task.getDescription(),
              task.getEpic_id(), task.getPriority(), task.getStatus(),
              task.getType(), task.getEstimatedDeadline(), task.getRealDeadline(),
              task.getRealHours(), task.getEstimatedHours(), task.getUser_points()))
          .toList();
      return ResponseEntity.ok(taskResponseList);
    }catch (Exception e) {
      System.out.println("Error retrieving tasks for sprint: " + e.getMessage());
      return ResponseEntity.status(500).body("Error retrieving tasks for sprint");
    }
  }

}

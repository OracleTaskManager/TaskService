package com.Oracle.TaskService.controller;

import com.Oracle.TaskService.data.*;
import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.service.TaskAssignmentService;
import com.Oracle.TaskService.service.TaskService;
import jakarta.validation.Valid;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired private TaskService taskService;

  @Autowired private TaskAssignmentService taskAssignmentService;

  @PostMapping("/")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<?> createTask(@RequestBody @Valid TaskRegister taskRequest) {
    Task task = taskService.createTask(taskRequest);
    return ResponseEntity.ok(
        new TaskResponse(
            task.getTaskId(),
            task.getTitle(),
            task.getDescription(),
            task.getEpic_id(),
            task.getPriority(),
            task.getStatus(),
            task.getType(),
            task.getEstimatedDeadline(),
            task.getRealDeadline(),
            task.getUser_points(),
            task.getRealHours(),
            task.getEstimatedHours()));
  }

  @GetMapping("/my-tasks")
  public ResponseEntity<?> getMyTasks() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    Long userId;
    userId = (Long) principal;

    List<Task> tasks = taskService.findTasksByUser(userId);
    if (tasks.isEmpty()) {
      return new ResponseEntity<>("No tasks found for the user", HttpStatus.NOT_FOUND);
    }
    List<TaskResponse> taskResponses =
        tasks.stream()
            .map(
                task ->
                    new TaskResponse(
                        task.getTaskId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getEpic_id(),
                        task.getPriority(),
                        task.getStatus(),
                        task.getType(),
                        task.getEstimatedDeadline(),
                        task.getRealDeadline(),
                        task.getRealHours(),
                        task.getEstimatedHours(),
                        task.getUser_points()))
            .toList();

    return new ResponseEntity<>(taskResponses, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('Manager')")
  @GetMapping("/all")
  public ResponseEntity<?> findAll() {
    return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/task/{task_id}")
  public ResponseEntity<?> findTaskById(@PathVariable("task_id") Long task_id) {
    return new ResponseEntity<>(taskService.findTaskById(task_id), HttpStatus.OK);
  }

  @GetMapping("/user-points/{user_points}")
  public ResponseEntity<?> findByUserPoints(@PathVariable("user_points") int user_points) {
    return new ResponseEntity<>(taskService.findByUserPoints(user_points), HttpStatus.OK);
  }

  @GetMapping("/priority/{priority}")
  public ResponseEntity<?> findByPriority(@PathVariable("priority") String priority) {
    return new ResponseEntity<>(taskService.findByPriority(priority), HttpStatus.OK);
  }

  @GetMapping("/type/{type}")
  public ResponseEntity<?> findByType(@PathVariable("type") String type) {
    return new ResponseEntity<>(taskService.findByType(type), HttpStatus.OK);
  }

  @GetMapping("/status/{status}")
  public ResponseEntity<?> findByStatus(@PathVariable("status") String status) {
    return new ResponseEntity<>(taskService.findByStatus(status), HttpStatus.OK);
  }

  @GetMapping("/title/{title}")
  public ResponseEntity<?> findByTitle(@PathVariable("title") String title) {
    return new ResponseEntity<>(taskService.findByTitle(title), HttpStatus.OK);
  }

  @GetMapping("/realhours/{hours}")
  public ResponseEntity<?> findByRealHours(@PathVariable("hours") Integer realHours) {
    return new ResponseEntity<>(taskService.findByRealHours(realHours), HttpStatus.OK);
  }

  @GetMapping("/estimatedhours/{hours}")
  public ResponseEntity<?> findByTitle(@PathVariable("hours") Integer estimatedHours) {
    return new ResponseEntity<>(taskService.findByEstimatedHours(estimatedHours), HttpStatus.OK);
  }

  //implement in service
  @PostMapping("/change-status/{task_id}")
  public ResponseEntity<?> changeStatus(@RequestBody @Valid TaskUpdateStatus taskUpdateStatus, @PathVariable("task_id") Long task_id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    Long userId;

    userId = (Long) principal;
    System.out.println("User ID: " + userId);
    Boolean isTaskAssigned =
        taskAssignmentService.isTaskAssigned(task_id, userId);

    if (!isTaskAssigned) {
      return new ResponseEntity<>("Task is not assigned to the user", HttpStatus.FORBIDDEN);
    }

    Task task = taskService.updateTaskStatus(task_id, taskUpdateStatus);

    if (task == null) {
      return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(
        new TaskResponse(
            task.getTaskId(),
            task.getTitle(),
            task.getDescription(),
            task.getEpic_id(),
            task.getPriority(),
            task.getStatus(),
            task.getType(),
            task.getEstimatedDeadline(),
            task.getRealDeadline(),
            task.getRealHours(),
            task.getEstimatedHours(),
            task.getUser_points()),
        HttpStatus.OK);
  }

  @GetMapping("/kpi-tasks")
  public List<TaskKPIView> getKpiTasks(
      @RequestParam String status, @RequestParam Date from, @RequestParam Date to) {
    return taskService.findByStatusAndRealDeadlineBetween(
        status, from, to); // map internally to TaskKpiView
  }

  @PreAuthorize("hasRole('Manager')")
  @DeleteMapping("/")
  public ResponseEntity<?> deleteTask(@RequestParam("task_id") Long task_id) {
    try{
      String url = "http://140.84.189.81/api/files/attachments/" + task_id;
      RestTemplate restTemplate = new RestTemplate();

      ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
      if (!response.getStatusCode().is2xxSuccessful()) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }

      if (taskService.deleteTaskById(task_id)) {
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    } catch(Exception e){
      System.out.println("Error during task deletion: " + e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @PutMapping("/update-my-task/{task_id}")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<?> updateTask(@RequestBody @Valid TaskUpdateContent taskUpdateContent, @PathVariable("task_id") Long task_id) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Object principal = authentication.getPrincipal();
    Long userId;
    userId = (Long) principal;
    System.out.println("User ID: " + userId);
    Boolean isTaskAssigned = taskAssignmentService.isTaskAssigned(task_id, userId);

    if (!isTaskAssigned) {
      return new ResponseEntity<>("Task is not assigned to the user", HttpStatus.FORBIDDEN);
    }

    Task task = taskService.updateTaskById(task_id, taskUpdateContent);

    if (task == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("Task not found");
    }

    TaskResponse response = new TaskResponse(
            task.getTaskId(),
            task.getTitle(),
            task.getDescription(),
            task.getEpic_id(),
            task.getPriority(),
            task.getStatus(),
            task.getType(),
            task.getEstimatedDeadline(),
            task.getRealDeadline(),
            task.getUser_points(),
            task.getRealHours(),
            task.getEstimatedHours()
    );

    return ResponseEntity.ok(response);
  }

  @PreAuthorize("hasRole('Manager')")
  @PutMapping("/update-task/{task_id}")
  public ResponseEntity<?> updateTaskContent(@RequestBody @Valid TaskUpdateContent taskUpdateContent, @PathVariable("task_id") Long task_id) {
    Task task = taskService.updateTaskById(task_id, taskUpdateContent);

    if (task == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
              .body("Task not found");
    }

    TaskResponse response = new TaskResponse(
            task.getTaskId(),
            task.getTitle(),
            task.getDescription(),
            task.getEpic_id(),
            task.getPriority(),
            task.getStatus(),
            task.getType(),
            task.getEstimatedDeadline(),
            task.getRealDeadline(),
            task.getUser_points(),
            task.getRealHours(),
            task.getEstimatedHours()
    );

    return ResponseEntity.ok(response);
  }
}

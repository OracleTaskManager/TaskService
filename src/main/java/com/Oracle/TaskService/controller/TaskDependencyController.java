package com.Oracle.TaskService.controller;

import com.Oracle.TaskService.data.TaskDependencyRegister;
import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.service.TaskDependencyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task_dependencies")
public class TaskDependencyController {

  @Autowired private TaskDependencyService taskDependencyService;

  @PreAuthorize("hasRole('Manager')")
  @PostMapping("/")
  public ResponseEntity<?> saveTaskDependency(
      @RequestBody @Valid TaskDependencyRegister taskDependency) {
    return new ResponseEntity<>(taskDependencyService.save(taskDependency), HttpStatus.CREATED);
  }

  @GetMapping("/all")
  public ResponseEntity<?> findAll() {
    return new ResponseEntity<>(taskDependencyService.findAll(), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('Manager')")
  @GetMapping("/{task_dependency_id}")
  public ResponseEntity<?> getTaskDependencyId(
      @PathVariable("task_dependency_id") Long taskDependencyId) {
    return new ResponseEntity<>(
        taskDependencyService.getTaskDependency(taskDependencyId), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('Manager')")
  @GetMapping("/{task_id}")
  public ResponseEntity<?> findByTaskId(@PathVariable("task_id") Task taskId) {
    return new ResponseEntity<>(taskDependencyService.findByTaskId(taskId), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('Manager')")
  @GetMapping("/{blockedby_task_id}")
  public ResponseEntity<?> findByBlockedByTaskId(@PathVariable("blockedby_task_id") Task taskId) {
    return new ResponseEntity<>(taskDependencyService.findByBlockedByTaskId(taskId), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('Manager')")
  @DeleteMapping("/")
  public ResponseEntity<?> deleteTaskDependency(@RequestBody @Valid Long taskDependencyId) {
    if (taskDependencyService.delete(taskDependencyId)) {
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}

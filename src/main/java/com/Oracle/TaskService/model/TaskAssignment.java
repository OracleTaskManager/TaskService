package com.Oracle.TaskService.model;

import com.Oracle.TaskService.data.TaskAssignmentRequest;
import jakarta.persistence.*;

@Entity
@Table(name = "task_assignments")
public class TaskAssignment {

  @EmbeddedId private TaskAssignmentId id;

  public TaskAssignment() {}

  public TaskAssignment(TaskAssignmentRequest taskAssignmentRequest) {
    this.id = new TaskAssignmentId(taskAssignmentRequest.taskId(), taskAssignmentRequest.userId());
  }

  public TaskAssignmentId getId() {
    return id;
  }

  public void setId(TaskAssignmentId id) {
    this.id = id;
  }

  public Long getTask_id() {
    return id.getTask_id();
  }

  public void setTask_id(Long task_id) {
    this.id.setTask_id(task_id);
  }

  public Long getUser_id() {
    return id.getUser_id();
  }

  public void setUser_id(Long user_id) {
    this.id.setUser_id(user_id);
  }
}

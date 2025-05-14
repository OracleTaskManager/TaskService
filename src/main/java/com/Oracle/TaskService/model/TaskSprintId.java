package com.Oracle.TaskService.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TaskSprintId implements Serializable {

  private Long task_id;
  private Long sprint_id;

  public TaskSprintId() {}

  public TaskSprintId(Long task_id, Long sprint_id) {
    this.task_id = task_id;
    this.sprint_id = sprint_id;
  }

  public Long getTask_id() {
    return task_id;
  }

  public void setTask_id(Long task_id) {
    this.task_id = task_id;
  }

  public Long getSprint_id() {
    return sprint_id;
  }

  public void setSprint_id(Long sprint_id) {
    this.sprint_id = sprint_id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(task_id, sprint_id);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    TaskSprintId that = (TaskSprintId) obj;
    return Objects.equals(task_id, that.task_id) && Objects.equals(sprint_id, that.sprint_id);
  }
}

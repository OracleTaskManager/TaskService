package com.Oracle.TaskService.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TaskAssignmentId implements Serializable {

    private Long task_id;
    private Long user_id;

    public TaskAssignmentId() {}

    public TaskAssignmentId(Long task_id, Long user_id) {
        this.task_id = task_id;
        this.user_id = user_id;
    }

    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(task_id, user_id);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        TaskAssignmentId that = (TaskAssignmentId) obj;
        return Objects.equals(task_id, that.task_id) && Objects.equals(user_id, that.user_id);
    }
}

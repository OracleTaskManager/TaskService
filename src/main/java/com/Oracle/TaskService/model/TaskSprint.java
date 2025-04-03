package com.Oracle.TaskService.model;

import com.Oracle.TaskService.data.TaskSprintRegister;
import jakarta.persistence.*;

@Entity
@Table(name = "task_sprint")
public class TaskSprint {

    @EmbeddedId
    private TaskSprintId id;

    public TaskSprint() {}

    public TaskSprint(TaskSprintRegister taskSprintRegister) {
        this.id = new TaskSprintId(taskSprintRegister.taskId(), taskSprintRegister.sprintId());
    }

    public TaskSprintId getId() {
        return id;
    }

    public void setId(TaskSprintId id) {
        this.id = id;
    }

    public Long getTask_id() {
        return id.getTask_id();
    }

    public void setTask_id(Long task_id) {
        this.id.setTask_id(task_id);
    }

    public Long getSprint_id() {
        return id.getSprint_id();
    }

    public void setSprint_id(Long sprint_id) {
        this.id.setSprint_id(sprint_id);
    }

}

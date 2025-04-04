package com.Oracle.TaskService.model;

import com.Oracle.TaskService.data.TaskDependencyRegister;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Entity
@Table(name="task_dependencies")
public class TaskDependency {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="task_dependency_id")
    private Long taskDependecyId;

    @OneToOne
    @JoinColumn(name="task_id")
    private Task taskId;

    @OneToOne
    @JoinColumn(name="task_id")
    private Task blockedByTaskId; //VERIFY CORRECTNESS

    public Long getTaskDependecyId() {
        return taskDependecyId;
    }

    public void setTaskDependecyId(Long taskDependecyId) {
        this.taskDependecyId = taskDependecyId;
    }

    public Task getTaskId() {
        return taskId;
    }

    public void setTaskId(Task taskId) {
        this.taskId = taskId;
    }

    public Task getBlockedByTaskId() {
        return blockedByTaskId;
    }

    public void setBlockedByTaskId(Task blockedByTaskId) {
        this.blockedByTaskId = blockedByTaskId;
    }

    public TaskDependency(TaskDependencyRegister taskDependencyRegister) {
        this.taskId = taskDependencyRegister.taskId();
        this.blockedByTaskId = taskDependencyRegister.blockedByTaskId();
    }

    public TaskDependency() {
    }
}

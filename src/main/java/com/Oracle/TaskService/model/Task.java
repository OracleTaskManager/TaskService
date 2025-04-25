package com.Oracle.TaskService.model;

import com.Oracle.TaskService.data.TaskRegister;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or GenerationType.SEQUENCE if you're using sequences
    @Column(name = "task_id")
    private Long taskId;

    private String title;
    private String description;
    private Long epic_id;
    private String priority;
    private String status;
    private String type;
    @Column(name="estimated_deadline")
    private Date estimatedDeadline;
    @Column(name="real_deadline")
    private Date realDeadline;
    @Column(name="estimated_hours")
    private int estimatedHours;
    @Column(name="real_hours")
    private int realHours;
    @Column(name="user_points")
    private int userPoints;

    public Task() {}

    public Task(TaskRegister taskRegister){
        this.title = taskRegister.title();
        this.description = taskRegister.description();
        this.epic_id = taskRegister.epicId();
        this.priority = taskRegister.priority().toString();
        this.status = "ToDo";
        this.type = taskRegister.type().toString();
        this.estimatedDeadline = taskRegister.estimatedDeadline();
        this.realDeadline = taskRegister.realDeadline();
        this.userPoints = taskRegister.userPoints();
        this.estimatedHours = taskRegister.estimatedHours();
        this.realHours = taskRegister.realHours();

        //add completedAt

    }


    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEpic_id() {
        return epic_id;
    }

    public void setEpic_id(Long epic_id) {
        this.epic_id = epic_id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEstimated_deadline() {
        return estimatedDeadline;
    }

    public void setEstimatedDeadline(Date estimatedDeadline) {
        this.estimatedDeadline = estimatedDeadline;
    }

    public Date getReal_deadline() {
        return realDeadline;
    }

    public void setRealDeadline(Date realDeadline) {
        this.realDeadline = realDeadline;
    }

    public int getUser_points() {
        return userPoints;
    }

    public void setUser_points(int user_points) {
        this.userPoints = userPoints;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }

    public void setEstimatedHours(int estimatedHours) {
        this.estimatedHours = estimatedHours;
    }

    public int getRealHours() {
        return realHours;
    }

    public void setRealHours(int realHours) {
        this.realHours = realHours;
    }
}

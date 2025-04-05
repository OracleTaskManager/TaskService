package com.Oracle.TaskService.model;

import com.Oracle.TaskService.data.TaskRegister;
import jakarta.persistence.*;
import org.hibernate.annotations.GenerationTime;

import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or GenerationType.SEQUENCE if you're using sequences
    @Column(name = "task_id")
    private Long task_id;

    private String title;
    private String description;
    private Long epic_id;
    private String priority;
    private String status;
    private String type;
    private Date estimated_deadline;
    private Date real_deadline;
    private int user_points;

    public Task() {}

    public Task(TaskRegister taskRegister){
        this.title = taskRegister.title();
        this.description = taskRegister.description();
        this.epic_id = taskRegister.epicId();
        this.priority = taskRegister.priority().toString();
        this.status = "ToDo";
        this.type = taskRegister.type().toString();
        this.estimated_deadline = taskRegister.estimatedDeadline();
        this.real_deadline = taskRegister.realDeadline();
        this.user_points = taskRegister.userPoints();
    }


    public Long getTask_id() {
        return task_id;
    }

    public void setTask_id(Long task_id) {
        this.task_id = task_id;
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
        return estimated_deadline;
    }

    public void setEstimated_deadline(Date estimated_deadline) {
        this.estimated_deadline = estimated_deadline;
    }

    public Date getReal_deadline() {
        return real_deadline;
    }

    public void setReal_deadline(Date real_deadline) {
        this.real_deadline = real_deadline;
    }

    public int getUser_points() {
        return user_points;
    }

    public void setUser_points(int user_points) {
        this.user_points = user_points;
    }
}

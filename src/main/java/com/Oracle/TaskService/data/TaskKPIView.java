package com.Oracle.TaskService.data;

import java.util.Date;

public class TaskKPIView {
    private Long userId;
    private Double realHours;
    private Date realDeadline;
    private String status;

    public TaskKPIView() {
    }

    public TaskKPIView(Long userId, Double realHours, Date realDeadline, String status) {
        this.userId = userId;
        this.realHours = realHours;
        this.realDeadline = realDeadline;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getRealHours() {
        return realHours;
    }

    public void setRealHours(Double realHours) {
        this.realHours = realHours;
    }

    public Date getRealDeadline() {
        return realDeadline;
    }

    public void setCompletedAt(Date realDeadline) {
        this.realDeadline = realDeadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

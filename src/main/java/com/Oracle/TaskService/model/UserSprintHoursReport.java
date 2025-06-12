package com.Oracle.TaskService.model;

import java.util.List;

public class UserSprintHoursReport {
  private Long userId;
  private String userName;
  private Long sprintId;
  private String sprintName;
  private Integer totalHours;
  private List<TaskDetail> tasks;

  public static class TaskDetail {
    private Long taskId;
    private String taskTitle;
    private Integer realHours;
    private String status;

    public TaskDetail() {}

    public TaskDetail(Long taskId, String taskTitle, Integer realHours, String status) {
      this.taskId = taskId;
      this.taskTitle = taskTitle;
      this.realHours = realHours;
      this.status = status;
    }

    public Long getTaskId() {
      return taskId;
    }

    public void setTaskId(Long taskId) {
      this.taskId = taskId;
    }

    public String getTaskTitle() {
      return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
      this.taskTitle = taskTitle;
    }

    public Integer getRealHours() {
      return realHours;
    }

    public void setRealHours(Integer realHours) {
      this.realHours = realHours;
    }

    public String getStatus() {
      return status;
    }

    public void setStatus(String status) {
      this.status = status;
    }
  }

  public UserSprintHoursReport() {}

  public UserSprintHoursReport(
      Long userId,
      String userName,
      Long sprintId,
      String sprintName,
      Integer totalHours,
      List<TaskDetail> tasks) {
    this.userId = userId;
    this.userName = userName;
    this.sprintId = sprintId;
    this.sprintName = sprintName;
    this.totalHours = totalHours;
    this.tasks = tasks;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Long getSprintId() {
    return sprintId;
  }

  public void setSprintId(Long sprintId) {
    this.sprintId = sprintId;
  }

  public String getSprintName() {
    return sprintName;
  }

  public void setSprintName(String sprintName) {
    this.sprintName = sprintName;
  }

  public Integer getTotalHours() {
    return totalHours;
  }

  public void setTotalHours(Integer totalHours) {
    this.totalHours = totalHours;
  }

  public List<TaskDetail> getTasks() {
    return tasks;
  }

  public void setTasks(List<TaskDetail> tasks) {
    this.tasks = tasks;
  }
}

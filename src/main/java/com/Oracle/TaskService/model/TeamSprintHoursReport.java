package com.Oracle.TaskService.model;

import java.util.List;

public class TeamSprintHoursReport {
  private Long teamId;
  private String teamName;
  private Long sprintId;
  private String sprintName;
  private Integer totalHours;
  private List<UserHoursDetail> userDetails;

  public TeamSprintHoursReport() {}

  public TeamSprintHoursReport(
      Long teamId,
      String teamName,
      Long sprintId,
      String sprintName,
      Integer totalHours,
      List<UserHoursDetail> userDetails) {
    this.teamId = teamId;
    this.teamName = teamName;
    this.sprintId = sprintId;
    this.sprintName = sprintName;
    this.totalHours = totalHours;
    this.userDetails = userDetails;
  }

  public Long getTeamId() {
    return teamId;
  }

  public void setTeamId(Long teamId) {
    this.teamId = teamId;
  }

  public String getTeamName() {
    return teamName;
  }

  public void setTeamName(String teamName) {
    this.teamName = teamName;
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

  public List<UserHoursDetail> getUserDetails() {
    return userDetails;
  }

  public void setUserDetails(List<UserHoursDetail> userDetails) {
    this.userDetails = userDetails;
  }

  public static class UserHoursDetail {
    private Long userId;
    private String userName;
    private Integer totalHours;
    private List<TaskDetail> tasks;

    public UserHoursDetail() {}

    public UserHoursDetail(
        Long userId, String userName, Integer totalHours, List<TaskDetail> tasks) {
      this.userId = userId;
      this.userName = userName;
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

  public static class TaskDetail {
    private Long taskId;
    private String taskTitle;
    private Integer realHours;

    public TaskDetail() {}

    public TaskDetail(Long taskId, String taskTitle, Integer realHours) {
      this.taskId = taskId;
      this.taskTitle = taskTitle;
      this.realHours = realHours;
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
  }
}

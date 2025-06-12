package com.Oracle.TaskService.model;

import java.util.Date;
import java.util.List;

public class SprintHoursReport {
  private Long sprintId;
  private String sprintName;
  private Date startDate;
  private Date endDate;
  private Integer totalHours;
  private List<TaskHoursDetail> taskDetails;

  public static class TaskHoursDetail {
    private Long taskId;
    private String taskTitle;
    private Integer realHours;
    private Integer estimatedHours;

    public TaskHoursDetail() {}

    public TaskHoursDetail(
        Long taskId, String taskTitle, Integer realHours, Integer estimatedHours) {
      this.taskId = taskId;
      this.taskTitle = taskTitle;
      this.realHours = realHours;
      this.estimatedHours = estimatedHours;
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

    public Integer getEstimatedHours() {
      return estimatedHours;
    }

    public void setEstimatedHours(Integer estimatedHours) {
      this.estimatedHours = estimatedHours;
    }
  }

  public SprintHoursReport() {}

  public SprintHoursReport(
      Long sprintId,
      String sprintName,
      Date startDate,
      Date endDate,
      Integer totalHours,
      List<TaskHoursDetail> taskDetails) {
    this.sprintId = sprintId;
    this.sprintName = sprintName;
    this.startDate = startDate;
    this.endDate = endDate;
    this.totalHours = totalHours;
    this.taskDetails = taskDetails;
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

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Integer getTotalHours() {
    return totalHours;
  }

  public void setTotalHours(Integer totalHours) {
    this.totalHours = totalHours;
  }

  public List<TaskHoursDetail> getTaskDetails() {
    return taskDetails;
  }

  public void setTaskDetails(List<TaskHoursDetail> taskDetails) {
    this.taskDetails = taskDetails;
  }
}

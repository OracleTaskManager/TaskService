package com.Oracle.TaskService.model;

import com.Oracle.TaskService.data.SprintRegister;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "sprints")
public class Sprint {

  @Id
  @Column(name = "sprint_id", columnDefinition = "NUMBER", insertable = false, updatable = false)
  @org.hibernate.annotations.Generated(GenerationTime.INSERT)
  private Long sprintId;

  private String name;
  private Date start_date;
  private Date end_date;
  private String status;

  public Sprint() {}

  public Sprint(SprintRegister sprintRegister) {
    this.name = sprintRegister.name();
    this.start_date = sprintRegister.startDate();
    this.end_date = sprintRegister.endDate();
    this.status = "Planned";
  }

  public Long getSprintId() {
    return sprintId;
  }

  public void setSprintId(Long sprintId) {
    this.sprintId = sprintId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getStart_date() {
    return start_date;
  }

  public void setStart_date(Date start_date) {
    this.start_date = start_date;
  }

  public Date getEnd_date() {
    return end_date;
  }

  public void setEnd_date(Date end_date) {
    this.end_date = end_date;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}

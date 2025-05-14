package com.Oracle.TaskService.model;

import com.Oracle.TaskService.data.EpicRegister;
import jakarta.persistence.*;
import org.hibernate.annotations.GenerationTime;

@Entity
@Table(name = "epics")
public class Epic {
  @Id
  @Column(name = "epic_id", columnDefinition = "NUMBER", insertable = false, updatable = false)
  @org.hibernate.annotations.Generated(GenerationTime.INSERT)
  private Long epic_id;

  private String title;
  private String description;
  private String status;

  public Epic() {}

  public Epic(EpicRegister epicRegister) {
    this.title = epicRegister.title();
    this.description = epicRegister.description();
    this.status = "ToDo";
  }

  public Long getEpic_id() {
    return epic_id;
  }

  public void setEpic_id(Long epic_id) {
    this.epic_id = epic_id;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}

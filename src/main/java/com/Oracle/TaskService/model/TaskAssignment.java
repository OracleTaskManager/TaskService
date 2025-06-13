package com.Oracle.TaskService.model;

import com.Oracle.TaskService.data.TaskAssignmentRequest;
import com.Oracle.TaskService.data.TelegramTaskAssignmentResponse;
import jakarta.persistence.*;

@Entity
@Table(name = "task_assignments")
@SqlResultSetMapping(
        name = "TelegramTaskAssignmentResponseMapping",
        classes = @ConstructorResult(
                targetClass = TelegramTaskAssignmentResponse.class,
                columns = {
                        @ColumnResult(name = "taskId", type = Long.class),
                        @ColumnResult(name = "taskTitle", type = String.class),
                        @ColumnResult(name = "userId", type = Long.class),
                        @ColumnResult(name = "userName", type = String.class)
                }
        )
)
@NamedNativeQuery(
        name = "TaskAssignment.findAllTelegram",
        query = "SELECT ta.task_id AS taskId, " +
                "t.title AS taskTitle, " +
                "ta.user_id AS userId, " +
                "u.name AS userName " +
                "FROM admin.task_assignments ta " +
                "JOIN admin.tasks t ON ta.task_id = t.task_id " +
                "JOIN admin.users u ON ta.user_id = u.user_id",
        resultSetMapping = "TelegramTaskAssignmentResponseMapping"
)
public class TaskAssignment {

  @EmbeddedId private TaskAssignmentId id;

  public TaskAssignment() {}

  public TaskAssignment(TaskAssignmentRequest taskAssignmentRequest) {
    this.id = new TaskAssignmentId(taskAssignmentRequest.taskId(), taskAssignmentRequest.userId());
  }

  public TaskAssignmentId getId() {
    return id;
  }

  public void setId(TaskAssignmentId id) {
    this.id = id;
  }

  public Long getTask_id() {
    return id.getTask_id();
  }

  public void setTask_id(Long task_id) {
    this.id.setTask_id(task_id);
  }

  public Long getUser_id() {
    return id.getUser_id();
  }

  public void setUser_id(Long user_id) {
    this.id.setUser_id(user_id);
  }
}

package com.Oracle.TaskService.repository;

import com.Oracle.TaskService.model.TaskAssignment;
import com.Oracle.TaskService.model.TaskAssignmentId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, TaskAssignmentId> {

  @Query(
      "SELECT CASE WHEN COUNT(ta) > 0 THEN TRUE ELSE FALSE END FROM TaskAssignment ta WHERE ta.id.task_id = ?1 AND ta.id.user_id = ?2")
  boolean existsByTaskIdAndUserId(Long taskId, Long userId);

  @Query("SELECT t FROM TaskAssignment t WHERE t.id.user_id = :userId")
  List<TaskAssignment> findByUserId(Long userId);

  @Query(
      value =
          "SELECT DISTINCT ta.user_id "
              + "FROM admin.task_assignments ta "
              + "JOIN admin.task_sprint ts ON ta.task_id = ts.task_id "
              + "WHERE ts.sprint_id = :sprintId",
      nativeQuery = true)
  List<Long> findDistinctUserIdsInSprint(Long sprintId);
}

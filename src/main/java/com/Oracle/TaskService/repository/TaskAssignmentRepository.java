package com.Oracle.TaskService.repository;

import com.Oracle.TaskService.model.TaskAssignment;
import com.Oracle.TaskService.model.TaskAssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskAssignmentRepository extends JpaRepository<TaskAssignment, TaskAssignmentId> {

    @Query("SELECT CASE WHEN COUNT(ta) > 0 THEN TRUE ELSE FALSE END FROM TaskAssignment ta WHERE ta.id.task_id = ?1 AND ta.id.user_id = ?2")
    boolean existsByTaskIdAndUserId(Long taskId, Long userId);

    @Query("SELECT t FROM TaskAssignment t WHERE t.id.user_id = :userId")
    List<TaskAssignment> findByUserId(Long userId);

}

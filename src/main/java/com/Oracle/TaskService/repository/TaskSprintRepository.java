package com.Oracle.TaskService.repository;

import com.Oracle.TaskService.model.TaskSprint;
import com.Oracle.TaskService.model.TaskSprintId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskSprintRepository extends JpaRepository<TaskSprint, TaskSprintId> {

  @Modifying
  @Transactional
  @Query("DELETE FROM TaskSprint ts WHERE ts.id.sprint_id = ?1")
  void deleteAllBySprintId(Long sprint_id);

  @Query(
      "SELECT CASE WHEN COUNT(ts) > 0 THEN TRUE ELSE FALSE END FROM TaskSprint ts WHERE ts.id.task_id = ?1 AND ts.id.sprint_id = ?2")
  boolean existsByTaskIdAndSprintId(Long task_id, Long sprint_id);
}

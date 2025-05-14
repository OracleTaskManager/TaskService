package com.Oracle.TaskService.repository;

import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.model.TaskDependency;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDependencyRepository extends JpaRepository<TaskDependency, Long> {

  List<TaskDependency> findByTaskId(Task taskId);

  List<TaskDependency> findByBlockedByTaskId(Task blockedByTaskId);
}

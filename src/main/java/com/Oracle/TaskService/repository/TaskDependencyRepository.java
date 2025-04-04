package com.Oracle.TaskService.repository;

import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.model.TaskDependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDependencyRepository extends JpaRepository<TaskDependency, Long> {

    List<TaskDependency> findByTaskId(Task taskId);
    List<TaskDependency> findByBlockedByTaskId(Task blockedByTaskId);

}

package com.Oracle.TaskService.repository;

import com.Oracle.TaskService.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findTaskById(Long id);
    List<Task> findByStatus(String status);
    List<Task> findByTitle(String title);
    List<Task> findByStatus(String status, Pageable pageable);
    List<Task> findByEpicId(Long epicId);
    List<Task> findByPriority(String priority);
    List<Task> findByType(String type);
    List<Task> findByUserPoints(int userPoints);

}

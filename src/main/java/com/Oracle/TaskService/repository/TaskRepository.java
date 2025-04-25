package com.Oracle.TaskService.repository;

import com.Oracle.TaskService.data.TaskKPIView;
import com.Oracle.TaskService.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Task findByTaskId(Long taskId);
    List<Task> findByStatus(String status);
    List<Task> findByTitle(String title);
    List<Task> findByPriority(String priority);
    List<Task> findByType(String type);
    @Query("SELECT t FROM Task t WHERE t.userPoints = :userPoints")
    List<Task> findByUserPoints(@Param("userPoints") int userPoints);
    List<Task> findByRealHours(Integer realHours);
    List<Task> findByEstimatedHours(Integer estimatedHours);
    List<TaskKPIView> findByStatusAndRealDeadlineBetween(String status, Date startDate, Date endDate);
    List<Task> findByTaskIdIn(List<Long> taskIds);

}

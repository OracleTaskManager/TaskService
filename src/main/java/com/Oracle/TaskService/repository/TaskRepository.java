package com.Oracle.TaskService.repository;

import com.Oracle.TaskService.data.TaskKPIView;
import com.Oracle.TaskService.model.Task;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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

  // List<Task> findByTaskIdIn(List<Long> taskIds);

  @Query(
      value =
          "SELECT t.task_id, t.title, t.status, t.real_hours, ts.sprint_id "
              + "FROM admin.tasks t "
              + "JOIN admin.task_sprint ts ON t.task_id = ts.task_id "
              + "WHERE ts.sprint_id = :sprintId",
      nativeQuery = true)
  List<Map<String, Object>> findTasksBySprintId(@Param("sprintId") Long sprintId);

  @Query(
      value =
          "SELECT t.task_id, t.title, t.status, t.real_hours, ts.sprint_id, ta.user_id "
              + "FROM admin.tasks t "
              + "JOIN admin.task_sprint ts ON t.task_id = ts.task_id "
              + "JOIN admin.task_assignments ta ON t.task_id = ta.task_id "
              + "WHERE ts.sprint_id = :sprintId AND ta.user_id = :userId",
      nativeQuery = true)
  List<Map<String, Object>> findTasksBySprintIdAndUserId(
      @Param("sprintId") Long sprintId, @Param("userId") Long userId);

  @Query(
      value =
          "SELECT t.task_id, t.title, t.status, t.real_hours, ts.sprint_id, ta.user_id "
              + "FROM admin.tasks t "
              + "JOIN admin.task_sprint ts ON t.task_id = ts.task_id "
              + "JOIN admin.task_assignments ta ON t.task_id = ta.task_id "
              + "JOIN admin.user_teams ut ON ta.user_id = ut.user_id "
              + "WHERE ts.sprint_id = :sprintId AND ut.team_id = :teamId",
      nativeQuery = true)
  List<Map<String, Object>> findTasksBySprintIdAndTeamId(
      @Param("sprintId") Long sprintId, @Param("teamId") Long teamId);

  @Query(
      value =
          "SELECT t.task_id, t.title, t.status, t.real_hours, t.real_deadline, ts.sprint_id, ta.user_id "
              + "FROM admin.tasks t "
              + "JOIN admin.task_sprint ts ON t.task_id = ts.task_id "
              + "JOIN admin.task_assignments ta ON t.task_id = ta.task_id "
              + "WHERE ts.sprint_id = :sprintId AND ta.user_id = :userId AND t.status = 'Done'",
      nativeQuery = true)
  List<Map<String, Object>> findCompletedTasksBySprintIdAndUserId(
      @Param("sprintId") Long sprintId, @Param("userId") Long userId);

  @Query(
      value =
          "SELECT SUM(t.real_hours) "
              + "FROM admin.tasks t "
              + "JOIN admin.task_sprint ts ON t.task_id = ts.task_id "
              + "WHERE ts.sprint_id = :sprintId",
      nativeQuery = true)
  Integer getTotalHoursBySprintId(@Param("sprintId") Long sprintId);

  @Query(
      value =
          "SELECT SUM(t.real_hours) "
              + "FROM admin.tasks t "
              + "JOIN admin.task_sprint ts ON t.task_id = ts.task_id "
              + "JOIN admin.task_assignments ta ON t.task_id = ta.task_id "
              + "WHERE ts.sprint_id = :sprintId AND ta.user_id = :userId",
      nativeQuery = true)
  Integer getTotalHoursBySprintIdAndUserId(
      @Param("sprintId") Long sprintId, @Param("userId") Long userId);

  @Query(
      value =
          "SELECT COUNT(*) "
              + "FROM admin.tasks t "
              + "JOIN admin.task_sprint ts ON t.task_id = ts.task_id "
              + "JOIN admin.task_assignments ta ON t.task_id = ta.task_id "
              + "WHERE ts.sprint_id = :sprintId AND ta.user_id = :userId AND t.status = 'Done'",
      nativeQuery = true)
  Integer countCompletedTasksBySprintIdAndUserId(
      @Param("sprintId") Long sprintId, @Param("userId") Long userId);

  @Query(
      value =
          "SELECT DISTINCT team_id FROM admin.user_teams ut "
              + "JOIN admin.task_assignments ta ON ut.user_id = ta.user_id "
              + "JOIN admin.task_sprint ts ON ta.task_id = ts.task_id",
      nativeQuery = true)
  List<Long> findAllTeamIds();
}

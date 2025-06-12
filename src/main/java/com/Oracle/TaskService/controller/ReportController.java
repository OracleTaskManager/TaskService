package com.Oracle.TaskService.controller;

import com.Oracle.TaskService.model.SprintHoursReport;
import com.Oracle.TaskService.model.TeamSprintHoursReport;
import com.Oracle.TaskService.model.UserSprintHoursReport;
import com.Oracle.TaskService.model.UserTasksCompletedReport;
import com.Oracle.TaskService.service.ReportService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reports")
public class ReportController {

  @Autowired private ReportService reportService;

  // 1. Horas por sprint
  @GetMapping("/hours/sprint/{sprintId}")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<SprintHoursReport> getHoursPerSprint(@PathVariable Long sprintId) {
    return ResponseEntity.ok(reportService.getHoursPerSprint(sprintId));
  }

  @GetMapping("/hours/sprints")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<List<SprintHoursReport>> getHoursPerAllSprints() {
    return ResponseEntity.ok(reportService.getHoursPerAllSprints());
  }

  // 2. Horas trabajadas por sprint y por usuario
  @GetMapping("/hours/sprint/{sprintId}/user/{userId}")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<UserSprintHoursReport> getHoursPerSprintAndUser(
      @PathVariable Long sprintId, @PathVariable Long userId) {
    return ResponseEntity.ok(reportService.getHoursPerSprintAndUser(sprintId, userId));
  }

  @GetMapping("/hours/sprints/user/{userId}")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<List<UserSprintHoursReport>> getHoursPerAllSprintsAndUser(
      @PathVariable Long userId) {
    return ResponseEntity.ok(reportService.getHoursPerAllSprintsAndUser(userId));
  }

  @GetMapping("/hours/sprint/{sprintId}/users")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<List<UserSprintHoursReport>> getHoursPerSprintAndAllUsers(
      @PathVariable Long sprintId) {
    return ResponseEntity.ok(reportService.getHoursPerSprintAndAllUsers(sprintId));
  }

  // 3. Horas trabajadas por equipos
  @GetMapping("/hours/sprint/{sprintId}/team/{teamId}")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<TeamSprintHoursReport> getHoursPerSprintAndTeam(
      @PathVariable Long sprintId, @PathVariable Long teamId) {
    return ResponseEntity.ok(reportService.getHoursPerSprintAndTeam(sprintId, teamId));
  }

  @GetMapping("/hours/sprint/{sprintId}/teams")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<List<TeamSprintHoursReport>> getHoursPerSprintAndAllTeams(
      @PathVariable Long sprintId) {
    return ResponseEntity.ok(reportService.getHoursPerSprintAndAllTeams(sprintId));
  }

  // 4. Tareas terminadas por sprint y usuario
  @GetMapping("/tasks/completed/sprint/{sprintId}/user/{userId}")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<UserTasksCompletedReport> getTasksCompletedPerSprintAndUser(
      @PathVariable Long sprintId, @PathVariable Long userId) {
    return ResponseEntity.ok(reportService.getTasksCompletedPerSprintAndUser(sprintId, userId));
  }

  @GetMapping("/tasks/completed/sprints/user/{userId}")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<List<UserTasksCompletedReport>> getTasksCompletedPerAllSprintsAndUser(
      @PathVariable Long userId) {
    return ResponseEntity.ok(reportService.getTasksCompletedPerAllSprintsAndUser(userId));
  }

  @GetMapping("/tasks/completed/sprint/{sprintId}/users")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<List<UserTasksCompletedReport>> getTasksCompletedPerSprintAndAllUsers(
      @PathVariable Long sprintId) {
    return ResponseEntity.ok(reportService.getTasksCompletedPerSprintAndAllUsers(sprintId));
  }

  @GetMapping("/hours/sprints/users")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<List<UserSprintHoursReport>> getHoursPerAllSprintsAndAllUsers() {
    return ResponseEntity.ok(reportService.getHoursPerAllSprintsAndAllUsers());
  }

  // Horas por todos los sprints y un equipo específico
  @GetMapping("/hours/sprints/team/{teamId}")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<List<TeamSprintHoursReport>> getHoursPerAllSprintsAndTeam(
      @PathVariable Long teamId) {
    return ResponseEntity.ok(reportService.getHoursPerAllSprintsAndTeam(teamId));
  }

  // Horas por todos los sprints y todos los equipos
  @GetMapping("/hours/sprints/teams")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<List<TeamSprintHoursReport>> getHoursPerAllSprintsAndAllTeams() {
    return ResponseEntity.ok(reportService.getHoursPerAllSprintsAndAllTeams());
  }

  // Tareas completadas por todos los usuarios en todos los sprints
  @GetMapping("/tasks/completed/sprints/users")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<List<UserTasksCompletedReport>>
      getTasksCompletedPerAllSprintsAndAllUsers() {
    System.out.println("Hola yo soy Mateo");

    return ResponseEntity.ok(reportService.getTasksCompletedPerAllSprintsAndAllUsers());
  }
}

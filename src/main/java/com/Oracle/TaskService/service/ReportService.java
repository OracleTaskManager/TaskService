package com.Oracle.TaskService.service;

import com.Oracle.TaskService.client.AuthServiceClient;
import com.Oracle.TaskService.model.SprintHoursReport;
import com.Oracle.TaskService.model.TeamSprintHoursReport;
import com.Oracle.TaskService.model.UserSprintHoursReport;
import com.Oracle.TaskService.model.UserTasksCompletedReport;
import com.Oracle.TaskService.repository.SprintRepository;
import com.Oracle.TaskService.repository.TaskAssignmentRepository;
import com.Oracle.TaskService.repository.TaskRepository;
import com.Oracle.TaskService.repository.TaskSprintRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
@Slf4j
public class ReportService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private TaskAssignmentRepository taskAssignmentRepository;

    @Autowired
    private TaskSprintRepository taskSprintRepository;

    @Autowired
    private AuthServiceClient authServiceClient;

    /**
     * Obtiene las horas trabajadas para un sprint específico
     */
    public SprintHoursReport getHoursPerSprint(Long sprintId) {
        var sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint not found: " + sprintId));

        var taskDetails = taskRepository.findTasksBySprintId(sprintId)
                .stream()
                .map(task -> new SprintHoursReport.TaskHoursDetail(
                        ((Number) task.get("task_id")).longValue(),
                        (String) task.get("title"),
                        task.get("real_hours") != null ? ((Number) task.get("real_hours")).intValue() : 0,
                        task.get("estimated_hours") != null ? ((Number) task.get("estimated_hours")).intValue() : 0
                ))
                .collect(Collectors.toList());

        Integer totalHours = taskRepository.getTotalHoursBySprintId(sprintId);

        return new SprintHoursReport(
                sprint.getSprintId(),
                sprint.getName(),
                sprint.getStart_date(),
                sprint.getEnd_date(),
                totalHours != null ? totalHours : 0,
                taskDetails
        );
    }

    /**
     * Obtiene las horas trabajadas por cada sprint
     */
    public List<SprintHoursReport> getHoursPerAllSprints() {
        return sprintRepository.findAll()
                .stream()
                .map(sprint -> getHoursPerSprint(sprint.getSprintId()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene las horas trabajadas por un usuario en un sprint específico
     */
    public UserSprintHoursReport getHoursPerSprintAndUser(Long sprintId, Long userId) {
        var sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint not found: " + sprintId));

        Map<String, Object> user;
        try {
            user = authServiceClient.getUserById(userId);
        } catch (Exception e) {
            System.out.println("Error geting user" + e.getMessage());
            user = Map.of("user_id", userId, "name", "Unknown User");
        }

        var tasks = taskRepository.findTasksBySprintIdAndUserId(sprintId, userId)
                .stream()
                .map(task -> new UserSprintHoursReport.TaskDetail(
                        ((Number) task.get("task_id")).longValue(),
                        (String) task.get("title"),
                        task.get("real_hours") != null ? ((Number) task.get("real_hours")).intValue() : 0,
                        (String) task.get("status")
                ))
                .collect(Collectors.toList());

        Integer totalHours = taskRepository.getTotalHoursBySprintIdAndUserId(sprintId, userId);

        return new UserSprintHoursReport(
                userId,
                (String) user.get("name"),
                sprint.getSprintId(),
                sprint.getName(),
                totalHours != null ? totalHours : 0,
                tasks
        );
    }

    /**
     * Obtiene las horas trabajadas por un usuario en todos los sprints
     */
    public List<UserSprintHoursReport> getHoursPerAllSprintsAndUser(Long userId) {
        return sprintRepository.findAll()
                .stream()
                .map(sprint -> getHoursPerSprintAndUser(sprint.getSprintId(), userId))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene las horas trabajadas por todos los usuarios en un sprint específico
     */
    public List<UserSprintHoursReport> getHoursPerSprintAndAllUsers(Long sprintId) {
        // Este método requiere obtener todos los usuarios del sistema
        // Como está en otro microservicio, tendríamos que consultar la lista de usuarios
        // Para este ejemplo, obtendremos los usuarios que tienen tareas asignadas en el sprint

        var userIds = taskAssignmentRepository.findDistinctUserIdsInSprint(sprintId);

        return userIds.stream()
                .map(userId -> getHoursPerSprintAndUser(sprintId, userId))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene las horas trabajadas por equipo en un sprint específico
     */
    public TeamSprintHoursReport getHoursPerSprintAndTeam(Long sprintId, Long teamId) {
        var sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint not found: " + sprintId));

        Map<String, Object> team;
        try {
            team = authServiceClient.getTeamById(teamId);
        } catch (Exception e) {
            System.out.println("Error fetching team information" + e.getMessage());
            team = Map.of("team_id", teamId, "team_name", "Unknown Team");
        }

        List<Map<String, Object>> teamUsers;
        try {
            teamUsers = authServiceClient.getUsersByTeamId(teamId);
            System.out.println(teamUsers);
        } catch (Exception e) {
            System.out.println("Error fetching team users"+ e.getMessage());
            teamUsers = Collections.emptyList();
        }

        var userDetails = teamUsers.stream()
                .map(user -> {
                    Long userId = null;
                    if (user.get("userId") != null) {
                        userId = ((Number) user.get("userId")).longValue();
                    } else {
                        // Si user_id es nulo, puedes asignar un valor por defecto o manejarlo según tu lógica
                        userId = 0L; // o cualquier otro valor por defecto apropiado
                        System.out.println("Warning: user_id is null for a user in team " + teamId);
                    }

                    String userName = user.get("name") != null ? (String) user.get("name") : "Unknown User";

                    var tasks = taskRepository.findTasksBySprintIdAndUserId(sprintId, userId)
                            .stream()
                            .map(task -> new TeamSprintHoursReport.TaskDetail(
                                    ((Number) task.get("task_id")).longValue(),
                                    (String) task.get("title"),
                                    task.get("real_hours") != null ? ((Number) task.get("real_hours")).intValue() : 0
                            ))
                            .collect(Collectors.toList());

                    Integer userHours = taskRepository.getTotalHoursBySprintIdAndUserId(sprintId, userId);

                    return new TeamSprintHoursReport.UserHoursDetail(
                            userId,
                            userName,
                            userHours != null ? userHours : 0,
                            tasks
                    );
                })
                .collect(Collectors.toList());

        // Calcular el total de horas del equipo
        Integer totalHours = userDetails.stream()
                .mapToInt(TeamSprintHoursReport.UserHoursDetail::getTotalHours)
                .sum();

        return new TeamSprintHoursReport(
                teamId,
                (String) team.get("team_name"),
                sprint.getSprintId(),
                sprint.getName(),
                totalHours,
                userDetails
        );
    }

    /**
     * Obtiene las horas trabajadas por todos los equipos en un sprint específico
     */
    public List<TeamSprintHoursReport> getHoursPerSprintAndAllTeams(Long sprintId) {
        // Obtenemos los equipos que han participado en el sprint
        // Esto requiere hacer una consulta compleja que involucra varias tablas
        // Para simplificar, podríamos usar la información de equipo de usuario

        List<Long> teamIds = new ArrayList<>();
        // Aquí habría que implementar la lógica para obtener los equipos involucrados en el sprint
        // Como es otro microservicio, podríamos obtener esta información mediante APIs

        return teamIds.stream()
                .map(teamId -> getHoursPerSprintAndTeam(sprintId, teamId))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene las tareas completadas por un usuario en un sprint específico
     */
    public UserTasksCompletedReport getTasksCompletedPerSprintAndUser(Long sprintId, Long userId) {
        var sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new RuntimeException("Sprint not found: " + sprintId));

        Map<String, Object> user;
        try {
            user = authServiceClient.getUserById(userId);
        } catch (Exception e) {
            System.out.println("Error fetching user information" + e.getMessage());
            user = Map.of("user_id", userId, "name", "Unknown User");
        }

        var completedTasks = taskRepository.findCompletedTasksBySprintIdAndUserId(sprintId, userId)
                .stream()
                .map(task -> new UserTasksCompletedReport.CompletedTask(
                        ((Number) task.get("task_id")).longValue(),
                        (String) task.get("title"),
                        (Date) task.get("real_deadline"),
                        task.get("real_hours") != null ? ((Number) task.get("real_hours")).intValue() : 0
                ))
                .collect(Collectors.toList());

        Integer tasksCount = taskRepository.countCompletedTasksBySprintIdAndUserId(sprintId, userId);

        return new UserTasksCompletedReport(
                userId,
                (String) user.get("name"),
                sprint.getSprintId(),
                sprint.getName(),
                tasksCount != null ? tasksCount : 0,
                completedTasks
        );
    }

    /**
     * Obtiene las tareas completadas por un usuario en todos los sprints
     */
    public List<UserTasksCompletedReport> getTasksCompletedPerAllSprintsAndUser(Long userId) {
        return sprintRepository.findAll()
                .stream()
                .map(sprint -> getTasksCompletedPerSprintAndUser(sprint.getSprintId(), userId))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene las tareas completadas por todos los usuarios en un sprint específico
     */
    public List<UserTasksCompletedReport> getTasksCompletedPerSprintAndAllUsers(Long sprintId) {
        var userIds = taskAssignmentRepository.findDistinctUserIdsInSprint(sprintId);

        return userIds.stream()
                .map(userId -> getTasksCompletedPerSprintAndUser(sprintId, userId))
                .collect(Collectors.toList());
    }

    public List<UserSprintHoursReport> getHoursPerAllSprintsAndAllUsers() {
        List<UserSprintHoursReport> reports = new ArrayList<>();

        // Obtenemos todos los sprints
        var sprints = sprintRepository.findAll();

        // Por cada sprint, obtenemos todos los usuarios asignados y sus horas
        for (var sprint : sprints) {
            var userReports = getHoursPerSprintAndAllUsers(sprint.getSprintId());
            reports.addAll(userReports);
        }

        return reports;
    }

    /**
     * Obtiene las horas trabajadas por un equipo específico en todos los sprints
     */
    public List<TeamSprintHoursReport> getHoursPerAllSprintsAndTeam(Long teamId) {
        List<TeamSprintHoursReport> reports = new ArrayList<>();

        // Obtenemos todos los sprints
        var sprints = sprintRepository.findAll();

        // Por cada sprint, obtenemos las horas del equipo
        for (var sprint : sprints) {
            var teamReport = getHoursPerSprintAndTeam(sprint.getSprintId(), teamId);
            reports.add(teamReport);
        }

        return reports;
    }

    /**
     * Obtiene las horas trabajadas por todos los equipos en todos los sprints
     */
    public List<TeamSprintHoursReport> getHoursPerAllSprintsAndAllTeams() {
        List<TeamSprintHoursReport> reports = new ArrayList<>();

        // Obtenemos todos los sprints
        var sprints = sprintRepository.findAll();

        // Obtenemos todos los equipos que han participado en algún sprint
        List<Long> teamIds = getTeamsInvolved();

        // Por cada sprint y cada equipo, obtenemos las horas
        for (var sprint : sprints) {
            for (var teamId : teamIds) {
                var teamReport = getHoursPerSprintAndTeam(sprint.getSprintId(), teamId);
                reports.add(teamReport);
            }
        }

        return reports;
    }

    /**
     * Obtiene las tareas completadas por todos los usuarios en todos los sprints
     */
    public List<UserTasksCompletedReport> getTasksCompletedPerAllSprintsAndAllUsers() {
        List<UserTasksCompletedReport> reports = new ArrayList<>();

        // Obtenemos todos los sprints
        var sprints = sprintRepository.findAll();

        // Por cada sprint, obtenemos las tareas completadas por todos los usuarios
        for (var sprint : sprints) {
            var sprintReports = getTasksCompletedPerSprintAndAllUsers(sprint.getSprintId());
            reports.addAll(sprintReports);
        }

        return reports;
    }

    /**
     * Método auxiliar para obtener todos los equipos involucrados en algún sprint
     */
    private List<Long> getTeamsInvolved() {
        // Esta implementación depende de cómo estén relacionados los datos
        // Si tienes que obtener esta información del servicio de autenticación,
        // aquí deberías hacer la llamada correspondiente

        try {
            // Ejemplo de implementación con una consulta a la base de datos
            @SuppressWarnings("unchecked")
            List<Long> teamIds = (List<Long>) taskRepository.findAllTeamIds();
            return teamIds;
        } catch (Exception e) {
            System.out.println("Error fetching teams involved" + e.getMessage());
            return Collections.emptyList();
        }
    }
}
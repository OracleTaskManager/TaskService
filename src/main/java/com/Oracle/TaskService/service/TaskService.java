package com.Oracle.TaskService.service;

import com.Oracle.TaskService.data.*;
import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.model.TaskAssignment;
import com.Oracle.TaskService.repository.TaskAssignmentRepository;
import com.Oracle.TaskService.repository.TaskRepository;

import java.lang.annotation.Documented;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

  @Autowired private TaskRepository taskRepository;

  @Autowired private TaskAssignmentRepository taskAssignmentRepository;

  public Task createTask(TaskRegister taskRegister) {
    Task task = new Task(taskRegister);
    return taskRepository.save(task);
  }

  public List<Task> findAll() {
    return taskRepository.findAll();
  }

  public Optional<Task> findTaskById(Long task_id) {
    return Optional.ofNullable(taskRepository.findById(task_id).orElse(null));
  }

  public Optional<Task> findTaskByEpicId(Long epicId) {
    return taskRepository.findById(epicId);
  }

  public List<Task> findByUserPoints(int user_points) {
    return taskRepository.findByUserPoints(user_points);
  }

  public List<Task> findByPriority(String priority) {
    return taskRepository.findByPriority(priority);
  }

  public List<Task> findByType(String type) {
    return taskRepository.findByType(type);
  }

  public List<Task> findByStatus(String status) {
    return taskRepository.findByStatus(status);
  }

  public List<Task> findByTitle(String title) {
    return taskRepository.findByTitle(title);
  }

  public Task updateTaskStatus(Long taskId, TaskUpdateStatus taskUpdateStatus) {
    Task task = taskRepository.findByTaskId(taskId);
    if (taskUpdateStatus.status() != null) {
      task.setStatus(taskUpdateStatus.status().toString());
    }
    if (taskUpdateStatus.realHours() != null) {
      task.setRealHours(taskUpdateStatus.realHours());
    }
    if (taskUpdateStatus.realDeadline() != null) {
      task.setRealDeadline(taskUpdateStatus.realDeadline());
    }

    return taskRepository.save(task);
  }

  public List<Task> findTasksByUser(Long userId) {
    List<TaskAssignment> taskAssignments = taskAssignmentRepository.findByUserId(userId);
    List<Long> taskIds =
        taskAssignments.stream().map(assignment -> assignment.getTask_id()).toList();
    List<Task> tasks = taskRepository.findAllById(taskIds);

    return tasks;
  }

  // delete
  public boolean deleteTaskById(Long id) {
    if (findTaskById(id).isPresent()) {
      taskRepository.deleteById(id);
      return true;
    } else {
      return false;
    }
  }

  public List<Task> findByRealHours(Integer realHours) {
    return taskRepository.findByRealHours(realHours);
  }

  public List<Task> findByEstimatedHours(Integer estimatedHours) {
    return taskRepository.findByEstimatedHours(estimatedHours);
  }

  public List<TaskKPIView> findByStatusAndRealDeadlineBetween(
      String status, Date startDate, Date endDate) {
    return taskRepository.findByStatusAndRealDeadlineBetween(status, startDate, endDate);
  }

  public FinancialResponse calculateFinancialBalance(Double hourlyRate) {
    List<Task> tasks = findAll();

    double totalEstimatedCost = tasks.stream()
            .mapToDouble(task -> (task.getEstimatedHours() != null ? task.getEstimatedHours() : 0) * hourlyRate)
            .sum();

    double totalRealCost = tasks.stream()
            .mapToDouble(task -> (task.getRealHours() != null ? task.getRealHours() : 0) * hourlyRate)
            .sum();

    double balance = totalEstimatedCost - totalRealCost;

    return new FinancialResponse(balance);
  }

  public Task updateTaskById(Long taskId, TaskUpdateContent updateTask) {
    Task task = taskRepository.findByTaskId(taskId);

    if (updateTask.title() != null) {
      task.setTitle(updateTask.title());
    }
    if (updateTask.description() != null) {
      task.setDescription(updateTask.description());
    }
    if (updateTask.epic_id() != null) {
      task.setEpic_id(updateTask.epic_id());
    }
    if (updateTask.priority() != null) {
      task.setPriority(updateTask.priority());
    }
    if (updateTask.status() != null) {
      task.setStatus(updateTask.status());
    }
    if (updateTask.type() != null) {
      task.setType(updateTask.type());
    }
    if (updateTask.estimated_deadline() != null) {
      task.setEstimatedDeadline(updateTask.estimated_deadline());
    }
    if (updateTask.real_deadline() != null) {
      task.setRealDeadline(updateTask.real_deadline());
    }
    if (updateTask.estimatedHours() != null) {
      task.setEstimatedHours(updateTask.estimatedHours());
    }
    if (updateTask.realHours() != null) {
      task.setRealHours(updateTask.realHours());
    }

    return taskRepository.save(task);
  }
}

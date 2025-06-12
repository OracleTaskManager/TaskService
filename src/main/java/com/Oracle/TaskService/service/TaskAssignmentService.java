package com.Oracle.TaskService.service;

import com.Oracle.TaskService.data.TaskAssignmentRequest;
import com.Oracle.TaskService.data.TaskAssignmentResponse;
import com.Oracle.TaskService.model.TaskAssignment;
import com.Oracle.TaskService.repository.TaskAssignmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TaskAssignmentService {

  @Autowired private TaskAssignmentRepository taskAssignmentRepository;

  public void addTaskAssignment(TaskAssignmentRequest taskAssignmentRequest) {
    TaskAssignment taskAssignment = new TaskAssignment(taskAssignmentRequest);
    taskAssignmentRepository.save(taskAssignment);
  }

  public void removeTaskAssignment(TaskAssignmentRequest taskAssignmentRequest) {
    TaskAssignment taskAssignment = new TaskAssignment(taskAssignmentRequest);
    taskAssignmentRepository.delete(taskAssignment);
  }

  public boolean isTaskAssigned(Long taskId, Long userId) {
    return taskAssignmentRepository.existsByTaskIdAndUserId(taskId, userId);
  }

  public List<?> getAllTaskAssignments() {
    List<TaskAssignment> taskAssignments = taskAssignmentRepository.findAll();
    if (taskAssignments.isEmpty()) {
      return List.of(ResponseEntity.notFound().build());
    }
    return taskAssignments.stream()
        .map(
            taskAssignment ->
                new TaskAssignmentResponse(
                    taskAssignment.getTask_id(), taskAssignment.getUser_id()))
        .toList();
  }
}

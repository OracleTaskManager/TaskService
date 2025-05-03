package com.Oracle.TaskService.service;

import com.Oracle.TaskService.data.TaskSprintRequest;
import com.Oracle.TaskService.model.TaskSprint;
import com.Oracle.TaskService.repository.TaskSprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskSprintService {

  @Autowired private TaskSprintRepository taskSprintRepository;

  public Object addTaskToSprint(TaskSprintRequest taskSprintRequest) {
    TaskSprint taskSprint = new TaskSprint(taskSprintRequest);
    return taskSprintRepository.save(taskSprint);
  }

  public void removeTaskFromSprint(TaskSprintRequest taskSprintRequest) {
    TaskSprint taskSprint = new TaskSprint(taskSprintRequest);
    taskSprintRepository.delete(taskSprint);
  }

  public void removeTasksFromSprint(Long sprint_id) {
    taskSprintRepository.deleteAllBySprintId(sprint_id);
  }

  public boolean isTaskInSprint(Long task_id, Long sprint_id) {
    return taskSprintRepository.existsByTaskIdAndSprintId(task_id, sprint_id);
  }
}

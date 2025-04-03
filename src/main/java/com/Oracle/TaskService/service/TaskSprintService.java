package com.Oracle.TaskService.service;

import com.Oracle.TaskService.data.TaskSprintRegister;
import com.Oracle.TaskService.model.TaskSprint;
import com.Oracle.TaskService.repository.TaskSprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskSprintService {

    @Autowired
    private TaskSprintRepository taskSprintRepository;

    public Object addTaskToSprint(TaskSprintRegister taskSprintRegister){
        TaskSprint taskSprint = new TaskSprint(taskSprintRegister);
        return taskSprintRepository.save(taskSprint);
    }

    public void removeTaskFromSprint(TaskSprintRegister taskSprintRegister){
        TaskSprint taskSprint = new TaskSprint(taskSprintRegister);
        taskSprintRepository.delete(taskSprint);
    }

    public void removeTasksFromSprint(Long sprint_id){
        taskSprintRepository.deleteAllBySprintId(sprint_id);
    }

    public boolean isTaskInSprint(Long task_id, Long sprint_id){
        return taskSprintRepository.existsByTaskIdAndSprintId(task_id, sprint_id);
    }

}

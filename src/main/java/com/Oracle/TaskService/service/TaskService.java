package com.Oracle.TaskService.service;

import com.Oracle.TaskService.data.TaskRegister;
import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(TaskRegister taskRegister){
        Task task = new Task(taskRegister);
        return taskRepository.save(task);
    }

}

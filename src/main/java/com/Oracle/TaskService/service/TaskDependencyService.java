package com.Oracle.TaskService.service;

import com.Oracle.TaskService.data.TaskDependencyRegister;
import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.model.TaskDependency;
import com.Oracle.TaskService.repository.TaskDependencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskDependencyService {

    @Autowired
    TaskDependencyRepository taskDependencyRepository;

    //create
    public TaskDependency save(TaskDependencyRegister taskDependencyRegister) {
        TaskDependency taskDependency = new TaskDependency(taskDependencyRegister);
        return taskDependencyRepository.save(taskDependency);
    }

    //gets
    public List<TaskDependency> findAll(){
        return taskDependencyRepository.findAll();
    }

    public Optional<TaskDependency> getTaskDependency(Long taskDependencyId){
        return taskDependencyRepository.findById(taskDependencyId);
    }

    public List<TaskDependency> findByTaskId(Task taskId) {
        return taskDependencyRepository.findByTaskId(taskId);
    }

    public List<TaskDependency> findByBlockedByTaskId(Task blockedByTaskId) {
        return taskDependencyRepository.findByBlockedByTaskId(blockedByTaskId);
    }

    //delete
    public boolean delete(Long taskDependencyId) {
        if(getTaskDependency(taskDependencyId).isPresent()){
            taskDependencyRepository.deleteById(taskDependencyId);
            return true;
        } else {
            return false;
        }
    }



}

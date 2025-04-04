package com.Oracle.TaskService.service;

import com.Oracle.TaskService.data.TaskRegister;
import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(TaskRegister taskRegister){
        Task task = new Task(taskRegister);
        return taskRepository.save(task);
    }

    //get
    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public Optional<Task> findTaskById(Long id){
        return Optional.ofNullable(taskRepository.findById(id).orElse(null));
    }

    public Optional<Task> findTaskByEpicId(Long epicId){
        return taskRepository.findById(epicId);
    }

    public List<Task> findByUserPoints(int userPoints){
        return taskRepository.findByUserPoints(userPoints);
    }
    public List<Task> findByPriority(String priority){
        return taskRepository.findByPriority(priority);
    }
    public List<Task> findByType(String type){
        return taskRepository.findByType(type);
    }
    public List<Task> findByStatus(String status){
        return taskRepository.findByStatus(status);
    }

    public List<Task> findByTitle(String title){
        return taskRepository.findByTitle(title);
    }

    //delete
    public boolean deleteTaskById(Long id){
        if(findTaskById(id).isPresent()){
            taskRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }


}

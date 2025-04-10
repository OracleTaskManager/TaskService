package com.Oracle.TaskService.service;

import com.Oracle.TaskService.data.TaskRegister;
import com.Oracle.TaskService.data.TaskUpdateStatus;
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

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public Optional<Task> findTaskById(Long task_id){
        return Optional.ofNullable(taskRepository.findById(task_id).orElse(null));
    }

    public Optional<Task> findTaskByEpicId(Long epicId){
        return taskRepository.findById(epicId);
    }

    public List<Task> findByUserPoints(int user_points){
        return taskRepository.findByUserPoints(user_points);
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

    public Task updateTaskStatus(TaskUpdateStatus taskUpdateStatus){
        Task task = taskRepository.findByTaskId(taskUpdateStatus.taskId());
        task.setStatus(taskUpdateStatus.status().toString());
        return taskRepository.save(task);
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

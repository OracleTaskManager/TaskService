package com.Oracle.TaskService.controller;

import com.Oracle.TaskService.data.TaskRegister;
import com.Oracle.TaskService.data.TaskResponse;
import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/")
    @PreAuthorize("hasRole('Manager')")
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskRegister taskRequest){
        Task task = taskService.createTask(taskRequest);
        return ResponseEntity.ok(new TaskResponse(
                task.getTask_id(),
                task.getTitle(),
                task.getDescription(),
                task.getEpic_id(),
                task.getPriority(),
                task.getStatus(),
                task.getType(),
                task.getEstimated_deadline(),
                task.getReal_deadline(),
                task.getUser_points()
                ));
    }

    @PreAuthorize("hasRole('Manager')")
    @GetMapping("/all")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{task_id}")
    public ResponseEntity<?> findTaskById(@PathVariable("task_id") Long task_id){
        return new ResponseEntity<>(taskService.findTaskById(task_id), HttpStatus.OK);
    }

    @GetMapping("/{user_points}")
    public ResponseEntity<?> findByUserPoints(@PathVariable("user_points") int user_points){
        return new ResponseEntity<>(taskService.findByUserPoints(user_points), HttpStatus.OK);
    }

    @GetMapping("/{priority}")
    public ResponseEntity<?> findByPriority(@PathVariable("priority") String priority){
        return new ResponseEntity<>(taskService.findByPriority(priority), HttpStatus.OK);
    }
    @GetMapping("/{type}")
    public ResponseEntity<?> findByType(@PathVariable("type") String type){
        return new ResponseEntity<>(taskService.findByType(type), HttpStatus.OK);
    }
    @GetMapping("/{epic_id}")
    public ResponseEntity<?> findTaskByEpicId(@PathVariable("epic_id") Long epic_id){
        return new ResponseEntity<>(taskService.findTaskByEpicId(epic_id), HttpStatus.OK);
    }
    @GetMapping("/{status}")
    public ResponseEntity<?> findByStatus(@PathVariable("status") String status){
        return new ResponseEntity<>(taskService.findByStatus(status), HttpStatus.OK);
    }
    @GetMapping("/{title}")
    public ResponseEntity<?> findByTitle(@PathVariable("title") String title){
        return new ResponseEntity<>(taskService.findByTitle(title), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('Manager')")
    @DeleteMapping("/")
    public ResponseEntity<?> deleteTask(@RequestParam("task_id") Long task_id){
        if(taskService.deleteTaskById(task_id)){
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

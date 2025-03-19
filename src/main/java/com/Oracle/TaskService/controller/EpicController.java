package com.Oracle.TaskService.controller;


import com.Oracle.TaskService.data.EpicRegister;
import com.Oracle.TaskService.data.EpicResponse;
import com.Oracle.TaskService.model.Epic;
import com.Oracle.TaskService.service.EpicService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/epics")
public class EpicController {

    @Autowired
    private EpicService epicService;

    @PostMapping
    @PreAuthorize("hasRole('Manager')")
    public ResponseEntity<?> createEpic(@RequestBody @Valid EpicRegister epicRequest){
        Epic epic = epicService.createEpic(epicRequest);
        return ResponseEntity.ok(new EpicResponse(epic.getEpic_id(),epic.getTitle(),epic.getDescription(),epic.getStatus()));
    }

}

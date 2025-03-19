package com.Oracle.TaskService.service;

import com.Oracle.TaskService.data.EpicRegister;
import com.Oracle.TaskService.model.Epic;
import com.Oracle.TaskService.repository.EpicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpicService {

    @Autowired
    private EpicRepository epicRepository;

    public Epic createEpic(EpicRegister epicRegister){
        Epic epic = new Epic(epicRegister);
        return epicRepository.save(epic);
    }



}

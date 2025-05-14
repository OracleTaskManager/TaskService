package com.Oracle.TaskService.service;

import com.Oracle.TaskService.data.EpicRegister;
import com.Oracle.TaskService.data.EpicUpdate;
import com.Oracle.TaskService.model.Epic;
import com.Oracle.TaskService.repository.EpicRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EpicService {

  @Autowired private EpicRepository epicRepository;

  public Epic createEpic(EpicRegister epicRegister) {
    Epic epic = new Epic(epicRegister);
    return epicRepository.save(epic);
  }

  // get epic
  public List<Epic> findAll() {
    return epicRepository.findAll();
  }

  public Optional<Epic> getEpicById(Long epic_id) {
    return epicRepository.findById(epic_id);
  }

  // delete epic
  public boolean deleteEpic(Long epic_id) {
    if (getEpicById(epic_id).isPresent()) {
      epicRepository.deleteById(epic_id);
      return true;
    } else {
      return false;
    }
  }

  public Epic updateEpic(EpicUpdate epicUpdate) {
    Epic epic = epicRepository.findById(epicUpdate.epicId()).get();
    if (epicUpdate.title() != null) {
      epic.setTitle(epicUpdate.title());
    }
    if (epicUpdate.description() != null) {
      epic.setDescription(epicUpdate.description());
    }
    if (epicUpdate.status() != null) {
      epic.setStatus(String.valueOf(epicUpdate.status()));
    }
    return epicRepository.save(epic);
  }
}

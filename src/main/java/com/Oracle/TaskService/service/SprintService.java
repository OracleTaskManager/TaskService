package com.Oracle.TaskService.service;

import com.Oracle.TaskService.data.SprintRegister;
import com.Oracle.TaskService.data.SprintUpdate;
import com.Oracle.TaskService.exceptions.SprintNotFoundException;
import com.Oracle.TaskService.model.Sprint;
import com.Oracle.TaskService.repository.SprintRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SprintService {

  @Autowired private SprintRepository sprintRepository;

  public Sprint saveSprint(SprintRegister sprintRegister) {
    Sprint sprint = new Sprint(sprintRegister);
    return sprintRepository.save(sprint);
  }

  public Sprint updateSprint(SprintUpdate sprintUpdate, Long sprintId) {
    Sprint sprint = sprintRepository.findById(sprintId)
            .orElseThrow(() -> new SprintNotFoundException("Sprint not found with ID: " + sprintId));
    if (sprintUpdate.name() != null) {
      sprint.setName(sprintUpdate.name());
    }
    if (sprintUpdate.startDate() != null) {
      sprint.setStart_date(sprintUpdate.startDate());
    }
    if (sprintUpdate.endDate() != null) {
      sprint.setEnd_date(sprintUpdate.endDate());
    }
    return sprintRepository.save(sprint);
  }

  public void deleteSprint(Long sprintId) {
    sprintRepository.deleteById(sprintId);
  }

  public List<Sprint> getSprints() {
    return sprintRepository.findAll();
  }

  public Sprint getSprint(Long sprintId) {
    return sprintRepository.findById(sprintId).get();
  }

  public List<Sprint> getSprints(String status) {
    return sprintRepository.findByStatus(status);
  }

  public boolean isPlanned(Long sprintId) {
    Sprint sprint = sprintRepository.findById(sprintId).get();
    return sprint.getStatus().equals("Planned");
  }

  public boolean isActive(Long sprintId) {
    Sprint sprint = sprintRepository.findById(sprintId).get();
    return sprint.getStatus().equals("Active");
  }

  public Sprint startSprint(Long sprintId) {
    Sprint sprint = sprintRepository.findById(sprintId).get();
    sprint.setStatus("Active");
    return sprintRepository.save(sprint);
  }
}

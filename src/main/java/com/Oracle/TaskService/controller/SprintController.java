package com.Oracle.TaskService.controller;

import com.Oracle.TaskService.data.SprintRegister;
import com.Oracle.TaskService.data.SprintResponse;
import com.Oracle.TaskService.data.SprintUpdate;
import com.Oracle.TaskService.data.enums.SprintStatus;
import com.Oracle.TaskService.model.Sprint;
import com.Oracle.TaskService.service.SprintService;
import com.Oracle.TaskService.service.TaskSprintService;
import jakarta.validation.Valid;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sprints")
public class SprintController {

  @Autowired private SprintService sprintService;

  @Autowired private TaskSprintService taskSprintService;

  @PostMapping("/")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<?> createSprint(@RequestBody @Valid SprintRegister sprintRegister) {
    try {
      Date currentDate = new Date();
      if (sprintRegister.startDate().before(currentDate)) {
        return ResponseEntity.status(400)
            .body("Cannot create a sprint with a start date in the past");
      }
      if (sprintRegister.endDate().before(sprintRegister.startDate())) {
        return ResponseEntity.status(400)
            .body("Cannot create a sprint with an end date before the start date");
      }

      Sprint sprint = sprintService.saveSprint(sprintRegister);
      SprintResponse sprintResponse =
          new SprintResponse(
              sprint.getSprintId(),
              sprint.getName(),
              sprint.getStart_date(),
              sprint.getEnd_date(),
              sprint.getStatus());

      return ResponseEntity.ok(sprintResponse);
    } catch (Exception e) {
      System.out.println("Error during sprint creation: " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  @GetMapping("/")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<?> getSprints(@RequestParam(required = false) SprintStatus status) {
    try {
      if (status == null) {
        return ResponseEntity.ok(sprintService.getSprints());
      }

      return ResponseEntity.ok(sprintService.getSprints(status.toString()));
    } catch (Exception e) {
      System.out.println("Error during sprint retrieval: " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  @PostMapping("/{sprintId}/start")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<?> startSprint(@PathVariable Long sprintId) {
    try {
      Boolean isPlanned = sprintService.isPlanned(sprintId);
      if (!isPlanned) {
        return ResponseEntity.status(400).body("Can only start planned sprints");
      }

      Date currentDate = new Date();
      Sprint sprint = sprintService.getSprint(sprintId);

      if (sprint.getStart_date().before(currentDate)) {
        return ResponseEntity.status(400)
            .body("Cannot start a sprint with a start date in the past");
      }

      sprintService.startSprint(sprintId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      System.out.println("Error during sprint start: " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

    @PostMapping("/{sprintId}/end")
    @PreAuthorize("hasRole('Manager')")
    public ResponseEntity<?> endSprint(@PathVariable Long sprintId) {
      try {
        Boolean isActive = sprintService.isActive(sprintId);
        if (!isActive) {
          return ResponseEntity.status(400).body("Can only end active sprints");
        }

        Sprint sprint = sprintService.getSprint(sprintId);
        Date currentDate = new Date();

        if (sprint.getEnd_date().before(currentDate)) {
          return ResponseEntity.status(400)
                  .body("Cannot end a sprint with an end date in the past");
        }

        sprintService.endSprint(sprintId);
        return ResponseEntity.ok().build();
      } catch (Exception e) {
        System.out.println("Error during sprint end: " + e.getMessage());
        return ResponseEntity.status(500).build();
      }
    }

  @PutMapping("/{sprintId}")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<?> updateSprint(@RequestBody @Valid SprintUpdate sprintUpdate, @PathVariable Long sprintId) {
    try {
//      boolean isActive = sprintService.isActive(sprintId);
//      if (isActive
//          && (sprintUpdate.endDate() != null
//              || sprintUpdate.startDate() != null
//              || sprintUpdate.name() != null)) {
//        return ResponseEntity.status(400).body("Cannot modify an active sprint");
//      }
//      Date currentDate = new Date();
      Sprint sprint = sprintService.getSprint(sprintId);
      // Both start and end date are required to be updated
//      if (sprintUpdate.startDate() != null && sprintUpdate.endDate() != null) {
//        // Start date cannot be in the past or after the end date
//        if (sprintUpdate.startDate().before(currentDate)
//            || sprintUpdate.startDate().after(sprintUpdate.endDate())) {
//          return ResponseEntity.status(400)
//              .body("Invalid start date, must be in the future and before the end date");
//        }
//        // End date cannot be before the start date or in the past
//        if (sprintUpdate.endDate().before(sprintUpdate.startDate())
//            || sprintUpdate.endDate().before(currentDate)) {
//          return ResponseEntity.status(400)
//              .body("Invalid end date, must be after the start date and in the future");
//        }
//      }
//      // Only start date is required to be updated
//      if (sprintUpdate.startDate() != null && sprintUpdate.endDate() == null) {
//        // Start date cannot be in the past
//        if (sprintUpdate.startDate().before(currentDate)) {
//          return ResponseEntity.status(400).body("Invalid start date, must be in the future");
//        }
//        // Start date cannot be after the end date
//        if (sprintUpdate.startDate().after(sprint.getEnd_date())) {
//          return ResponseEntity.status(400).body("Invalid start date, must be before the end date");
//        }
//      }
//      // Only end date is required to be updated
//      if (sprintUpdate.startDate() == null && sprintUpdate.endDate() != null) {
//        // End date cannot be before the start date
//        if (sprintUpdate.endDate().before(sprint.getStart_date())) {
//          return ResponseEntity.status(400).body("Invalid end date, must be after the start date");
//        }
//        // End date cannot be in the past
//        if (sprintUpdate.endDate().before(currentDate)) {
//          return ResponseEntity.status(400).body("Invalid end date, must be in the future");
//        }
//      }

      Sprint updatedSprint = sprintService.updateSprint(sprintUpdate, sprintId);
      SprintResponse sprintResponse =
          new SprintResponse(
              updatedSprint.getSprintId(),
              updatedSprint.getName(),
              updatedSprint.getStart_date(),
              updatedSprint.getEnd_date(),
              updatedSprint.getStatus());
      return ResponseEntity.ok(sprintResponse);

    } catch (Exception e) {
      System.out.println("Error during sprint update: " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }

  @DeleteMapping("/")
  @PreAuthorize("hasRole('Manager')")
  public ResponseEntity<?> deleteSprint(@RequestParam Long sprintId) {
    try {
      Boolean isPlanned = sprintService.isPlanned(sprintId);
      if (!isPlanned) {
        return ResponseEntity.status(400).body("Can only delete planned sprints");
      }

      taskSprintService.removeTasksFromSprint(sprintId);

      sprintService.deleteSprint(sprintId);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      System.out.println("Error during sprint deletion: " + e.getMessage());
      return ResponseEntity.status(500).build();
    }
  }
}

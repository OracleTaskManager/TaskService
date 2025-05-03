package com.Oracle.TaskService.repository;

import com.Oracle.TaskService.model.Sprint;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<Sprint, Long> {

  List<Sprint> findByStatus(String status);
}

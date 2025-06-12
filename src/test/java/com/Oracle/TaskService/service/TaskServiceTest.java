package com.Oracle.TaskService.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.repository.TaskRepository;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.*;

@Disabled
class TaskServiceTest {

  @Mock private TaskRepository taskRepository;

  @InjectMocks private TaskService taskService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this); // Initialize @Mock and @InjectMocks
  }

  // 4. Test findTaskById when not found
  @Test
  void findTaskById_shouldReturnEmptyWhenNotFound() {
    when(taskRepository.findById(99L)).thenReturn(Optional.empty());

    Optional<Task> result = taskService.findTaskById(99L);

    assertFalse(result.isPresent());
  }
}

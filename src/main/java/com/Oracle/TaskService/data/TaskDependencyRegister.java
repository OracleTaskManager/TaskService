package com.Oracle.TaskService.data;

import com.Oracle.TaskService.model.Task;
import jakarta.validation.constraints.NotNull;

public record TaskDependencyRegister(@NotNull Task taskId, @NotNull Task blockedByTaskId) {}

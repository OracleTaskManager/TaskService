package com.Oracle.TaskService.data;

import com.Oracle.TaskService.model.Task;
import com.Oracle.TaskService.model.TaskDependency;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record TaskDependencyRegister(

        @NotNull
        Task taskId,

        @NotNull
        Task blockedByTaskId
) {
}

package com.Oracle.TaskService.data;

public record TaskDependencyResponse(
        Long taskDependencyId,
        Long taskId,
        Long blockedByTaskId
) {
}

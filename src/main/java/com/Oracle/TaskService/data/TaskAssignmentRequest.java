package com.Oracle.TaskService.data;

public record TaskAssignmentRequest(
        Long taskId,
        Long userId
) {
}

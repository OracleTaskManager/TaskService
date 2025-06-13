package com.Oracle.TaskService.data;


public record TelegramTaskAssignmentResponse(
        Long taskId,
        String taskTitle,
        Long userId,
        String userName
) {
}

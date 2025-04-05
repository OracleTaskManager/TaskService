package com.Oracle.TaskService.data;

import com.Oracle.TaskService.data.enums.TaskStatus;

public record TaskUpdateStatus(
        Long task_id,
        TaskStatus status
) {
}

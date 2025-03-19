package com.Oracle.TaskService.data;

public record EpicResponse(
        Long epic_id,
        String title,
        String description,
        String status
) {
}

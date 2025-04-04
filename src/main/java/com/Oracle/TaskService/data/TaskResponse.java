package com.Oracle.TaskService.data;

import java.util.Date;

public record TaskResponse(
        Long id,
        String title,
        String description,
        Long epic_id,
        String priority,
        String status,
        String type,
        Date estimated_deadline,
        Date real_deadline,
        int user_points
) {

}

package com.Oracle.TaskService.data;

import java.util.Date;

public record TaskUpdateContent(
        Long id,
        String title,
        String description,
        Long epic_id,
        String priority,
        String status,
        String type,
        Date estimated_deadline,
        Date real_deadline,
        Integer realHours,
        Integer estimatedHours,
        Integer user_points) {}

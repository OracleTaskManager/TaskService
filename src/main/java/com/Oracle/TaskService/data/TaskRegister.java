package com.Oracle.TaskService.data;

import com.Oracle.TaskService.data.enums.Priority;
import com.Oracle.TaskService.data.enums.TaskStatus;
import com.Oracle.TaskService.data.enums.Type;

import java.util.Date;

public record TaskRegister(
        String title,
        String description,
        Long epic_id,
        Priority priority,
        Type type,
        Date estimated_deadline,
        Date real_deadline,
        int user_points
) {
}

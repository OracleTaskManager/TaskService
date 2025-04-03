package com.Oracle.TaskService.data;

import com.Oracle.TaskService.data.enums.Priority;
import com.Oracle.TaskService.data.enums.Type;

import java.util.Date;

public record TaskRegister(
        String title,
        String description,
        Long epicId,
        Priority priority,
        Type type,
        Date estimatedDeadline,
        Date realDeadline,
        int userPoints
) {
}

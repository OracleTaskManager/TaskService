package com.Oracle.TaskService.data;

import java.util.Date;

public record SprintRegister(
        String name,
        Date startDate,
        Date endDate
) {
}

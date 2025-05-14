package com.Oracle.TaskService.data;

import java.util.Date;

public record SprintResponse(
    Long sprint_id, String name, Date start_date, Date end_date, String status) {}

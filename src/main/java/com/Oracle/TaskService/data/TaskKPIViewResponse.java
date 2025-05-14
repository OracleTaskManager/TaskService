package com.Oracle.TaskService.data;

import java.util.Date;

public record TaskKPIViewResponse(
    Long userId, Double realHours, Date realDeadline, String status) {}

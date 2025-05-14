package com.Oracle.TaskService.data;

import java.util.Date;

public record SprintUpdate(Long sprintId, String name, Date startDate, Date endDate) {}

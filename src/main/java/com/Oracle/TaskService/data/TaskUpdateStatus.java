package com.Oracle.TaskService.data;

import com.Oracle.TaskService.data.enums.TaskStatus;
import java.util.Date;

public record TaskUpdateStatus(TaskStatus status, Date realDeadline, Integer realHours) {}

package com.Oracle.TaskService.data;

import com.Oracle.TaskService.data.enums.EpicStatus;

public record EpicUpdate(
        Long epicId,
        String title,
        String description,
        EpicStatus status
) {

}

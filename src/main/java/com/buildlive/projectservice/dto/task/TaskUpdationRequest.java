package com.buildlive.projectservice.dto.task;

import com.buildlive.projectservice.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskUpdationRequest {


    private String taskStatus;
}

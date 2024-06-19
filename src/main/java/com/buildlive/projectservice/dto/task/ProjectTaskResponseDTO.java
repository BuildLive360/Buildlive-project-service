package com.buildlive.projectservice.dto.task;


import com.buildlive.projectservice.dto.ProjectDto;
import com.buildlive.projectservice.dto.ProjectTeamDto;
import com.buildlive.projectservice.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTaskResponseDTO {


    private UUID id;
    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectTeamResponseDTO projectTeam;
    private ProjectResponseDTO project;
    private TaskStatus taskStatus;
}

package com.buildlive.projectservice.dto;

import com.buildlive.projectservice.entity.ProjectRole;
import com.buildlive.projectservice.entity.ProjectTeam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTeamDto {

    private UUID projectId;
    private ProjectTeam employee;
    private ProjectRole projectRole;
}

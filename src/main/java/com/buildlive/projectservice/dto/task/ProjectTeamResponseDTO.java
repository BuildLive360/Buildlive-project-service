package com.buildlive.projectservice.dto.task;

import com.buildlive.projectservice.entity.ProjectRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTeamResponseDTO {

    private UUID id;
    private String name;
    private ProjectRole projectRole;
}

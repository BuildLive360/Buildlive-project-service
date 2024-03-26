package com.buildlive.projectservice.dto;

import com.buildlive.projectservice.entity.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

    private String projectName;
    private String address;
    private LocalDate start_date;
    private LocalDate end_date;
    private String project_value;
    private ProjectStatus status;
    private UUID creator;
    private UUID company;
}

package com.buildlive.projectservice.dto;

import com.buildlive.projectservice.entity.ProjectStatus;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDto {

    private UUID id;
    private String projectName;
    private String address;
    private String city;
    private LocalDate start_date;
    private LocalDate end_date;
    private String project_value;
    private ProjectStatus status;
    private UUID creator;
    private UUID company;
}

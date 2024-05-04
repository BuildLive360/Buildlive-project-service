package com.buildlive.projectservice.dto;

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
public class ProjectTaskDto {

    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;
    private UUID assignedTo;
    private UUID projectId;
    private String partyEmail;
}

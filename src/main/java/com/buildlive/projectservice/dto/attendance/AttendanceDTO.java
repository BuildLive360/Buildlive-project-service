package com.buildlive.projectservice.dto.attendance;

import com.buildlive.projectservice.entity.ProjectRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AttendanceDTO {

    private UUID id;
    private UUID projectId;
    private UUID companyId;
    private UUID teamMemberId;
    private String projectName; // Assuming you have a field for project name
    private String projectDescription; // Assuming you have a field for project description
    private String teamMemberName; // Assuming you have a field for team member name
    private ProjectRole teamMemberRole; // Assuming you have a field for team member role
    private Long totalSalary;
    private Date date;
    private List<AttendanceWorkforceDTO> workforceAllocations;
}

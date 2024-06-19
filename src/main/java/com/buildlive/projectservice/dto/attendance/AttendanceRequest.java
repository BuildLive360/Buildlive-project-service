package com.buildlive.projectservice.dto.attendance;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRequest {


    private UUID projectId;
    private UUID companyId;
    private UUID teamMemberId;
    private Map<UUID, Integer> workforce;
    private Long totalSalary;
    private Date date;
}

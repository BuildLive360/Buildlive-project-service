package com.buildlive.projectservice.service;

import com.buildlive.projectservice.dto.attendance.AttendanceDTO;
import com.buildlive.projectservice.dto.attendance.AttendanceRequest;
import com.buildlive.projectservice.entity.AttendanceWorkforce;
import com.buildlive.projectservice.entity.ProjectAttendance;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IAttendanceService {

    void saveAttendance(AttendanceRequest attendanceRequest);
    ProjectAttendance save(ProjectAttendance projectAttendance, Map<UUID,Integer>selectedWorkForces);

    AttendanceWorkforce save(AttendanceWorkforce workforce);

    List<AttendanceDTO> getAttendanceData(UUID companyId, UUID projectId, Date date);
}

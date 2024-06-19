package com.buildlive.projectservice.controller;

import com.buildlive.projectservice.dto.attendance.AttendanceDTO;
import com.buildlive.projectservice.dto.attendance.AttendanceRequest;
import com.buildlive.projectservice.entity.ProjectAttendance;
import com.buildlive.projectservice.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/project")
@RestController
public class ProjectAttendanceController {

    @Autowired
    private IAttendanceService attendanceService;

//    @PostMapping("/attendance/mark-attendance")
//    public ResponseEntity<?> submitAttendance(@RequestBody AttendanceRequest attendanceRequest){
//        attendanceService.saveAttendance(attendanceRequest);
//        return ResponseEntity.ok().build();
//    }


    @PostMapping("/attendance/mark-attendance")
    public ResponseEntity<ProjectAttendance> saveAttendance(@RequestBody AttendanceRequest request) {
        ProjectAttendance attendance = new ProjectAttendance();
        attendance.setProjectId(request.getProjectId());
        attendance.setCompanyId(request.getCompanyId());
        attendance.setTeamMemberId(request.getTeamMemberId());
        attendance.setTotalSalary(request.getTotalSalary());
        attendance.setDate(request.getDate());

        ProjectAttendance savedAttendance = attendanceService.save(attendance,request.getWorkforce());
        return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
    }


    @GetMapping("/attendance")
    public ResponseEntity<List<AttendanceDTO>> getAttendanceData(
            @RequestParam UUID companyId,
            @RequestParam UUID projectId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date
    ) {
        List<AttendanceDTO> attendanceList = attendanceService.getAttendanceData(companyId, projectId, date);
        return ResponseEntity.ok(attendanceList);
    }

}

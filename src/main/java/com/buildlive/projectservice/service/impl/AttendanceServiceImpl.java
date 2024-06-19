package com.buildlive.projectservice.service.impl;

import com.buildlive.projectservice.dto.attendance.AttendanceDTO;
import com.buildlive.projectservice.dto.attendance.AttendanceRequest;
import com.buildlive.projectservice.dto.attendance.AttendanceWorkforceDTO;
import com.buildlive.projectservice.entity.AttendanceWorkforce;
import com.buildlive.projectservice.entity.Project;
import com.buildlive.projectservice.entity.ProjectAttendance;
import com.buildlive.projectservice.entity.ProjectTeam;
import com.buildlive.projectservice.repo.IAttendanceWorkforceRepository;
import com.buildlive.projectservice.repo.IProjectAttendanceRepository;
import com.buildlive.projectservice.repo.ProjectRepository;
import com.buildlive.projectservice.repo.ProjectTeamRepository;
import com.buildlive.projectservice.service.IAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

    @Autowired
    IAttendanceWorkforceRepository attendanceWorkforceRepository;

    @Autowired
    IProjectAttendanceRepository projectAttendanceRepository;

    @Autowired
    ProjectTeamRepository projectTeamRepository;

    @Autowired
    ProjectRepository projectRepository;


    @Override
    public void saveAttendance(AttendanceRequest attendanceRequest) {

        ProjectAttendance attendance = new ProjectAttendance();
        attendance.setProjectId(attendanceRequest.getProjectId());
        attendance.setTeamMemberId(attendanceRequest.getTeamMemberId());
//        attendance.setWorkforce(attendanceRequest.getWorkforce());
        attendance.setTotalSalary(attendanceRequest.getTotalSalary());
        attendance.setDate(attendanceRequest.getDate());

        projectAttendanceRepository.save(attendance);
    }

    @Override
    public ProjectAttendance save(ProjectAttendance projectAttendance, Map<UUID,Integer> selectedWorkforces) {

        Set<AttendanceWorkforce> workforceAllocations = new HashSet<>();
        for (Map.Entry<UUID, Integer> entry : selectedWorkforces.entrySet()) {
            AttendanceWorkforce workforce = new AttendanceWorkforce();
            workforce.setWorkforceId(entry.getKey());
            workforce.setQuantity(entry.getValue());
            workforce.setAttendance(projectAttendance); // Associate with the parent attendance
            workforceAllocations.add(workforce);
        }
        projectAttendance.setWorkforceAllocations(workforceAllocations);
        return projectAttendanceRepository.save(projectAttendance);
    }

    @Override
    public AttendanceWorkforce save(AttendanceWorkforce workforce) {
        return attendanceWorkforceRepository.save(workforce);
    }


    @Override
    public List<AttendanceDTO> getAttendanceData(UUID companyId, UUID projectId, Date date) {
       List<ProjectAttendance> attendances =  projectAttendanceRepository.findByCompanyIdAndProjectIdAndDate(companyId, projectId, date);
       List<AttendanceDTO> attendanceDTOS = new ArrayList<>();

       for (ProjectAttendance attendance: attendances){
           AttendanceDTO dto = new AttendanceDTO();
           dto.setId(attendance.getId());
           dto.setProjectId(attendance.getProjectId());
           dto.setCompanyId(attendance.getCompanyId());
           dto.setTeamMemberId(attendance.getTeamMemberId());
           dto.setTotalSalary(attendance.getTotalSalary());
           dto.setDate(attendance.getDate());


           Optional<Project> projectOpt = projectRepository.findById(attendance.getProjectId());
           projectOpt.ifPresent(project -> {
               dto.setProjectName(project.getProjectName());

           });


           Optional<ProjectTeam> teamMemberOpt = projectTeamRepository.findById(attendance.getTeamMemberId());
           teamMemberOpt.ifPresent(teamMember -> {
               dto.setTeamMemberName(teamMember.getName());
               dto.setTeamMemberRole(teamMember.getProjectRole());
           });


           List<AttendanceWorkforce> workforceAllocations = attendanceWorkforceRepository.findByAttendanceId(attendance.getId());
           List<AttendanceWorkforceDTO> workforceDTOs = workforceAllocations.stream()
                   .map(allocation -> {
                       AttendanceWorkforceDTO workforceDTO = new AttendanceWorkforceDTO();
                       workforceDTO.setWorkforceId(allocation.getWorkforceId());
                       workforceDTO.setQuantity(allocation.getQuantity());
                       return workforceDTO;
                   })
                   .collect(Collectors.toList());

           dto.setWorkforceAllocations(workforceDTOs);
           attendanceDTOS.add(dto);
       }
       return attendanceDTOS;
    }

}

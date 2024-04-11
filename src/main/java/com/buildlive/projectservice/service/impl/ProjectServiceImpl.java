package com.buildlive.projectservice.service.impl;

import com.buildlive.projectservice.dto.ProjectDto;
import com.buildlive.projectservice.dto.ProjectResponse;
import com.buildlive.projectservice.dto.ProjectTeamDto;
import com.buildlive.projectservice.dto.TeamRetrieval;
import com.buildlive.projectservice.entity.Project;
import com.buildlive.projectservice.entity.ProjectRole;
import com.buildlive.projectservice.entity.ProjectStatus;
import com.buildlive.projectservice.entity.ProjectTeam;
import com.buildlive.projectservice.repo.ProjectRepository;
import com.buildlive.projectservice.repo.ProjectTeamRepository;
import com.buildlive.projectservice.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    ModelMapper modelMapper;
    @Autowired
    public ProjectServiceImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;

    }



    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    ProjectTeamRepository projectTeamRepository;

    @Override
    public ProjectResponse createProject(ProjectDto projectDto) {

        try{
//            Project project = modelMapper.map(projectDto,Project.class);
////            project.setStatus(ProjectStatus.NOT_STARTED);
//            projectRepository.save(project);
//            return new ProjectResponse(HttpStatus.CREATED.value(),"Project Created");

            projectDto.setStatus(ProjectStatus.NOT_STARTED);

            // Map ProjectDto to Project entity
            Project project = modelMapper.map(projectDto, Project.class);

            // Save Project entity to the database
            projectRepository.save(project);

            // Map the saved Project entity back to ProjectDto
            ProjectDto savedProjectDto = modelMapper.map(project, ProjectDto.class);
            return new ProjectResponse(HttpStatus.CREATED.value(), "Project Created");
        }
        catch (Exception e){
            String errorMessage = "Failed" + e.getMessage();
            return new ProjectResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), errorMessage);
        }
    }

    @Override
    public List<Project> getAllByCompany(UUID companyId) {
        return projectRepository.findByCompany(companyId);
    }

    @Override
    public void addEmployeeToProjectTeam(ProjectTeamDto request) {

        Optional<Project> optionalProject = projectRepository.findById(request.getProjectId());
        if(optionalProject.isPresent()){
            Project project = optionalProject.get();
            if(isEmployeeAlreadyExists(project,request.getEmployee())){
                throw new IllegalArgumentException("Employee"+request.getEmployee().getName()+"exists");
            }

            ProjectTeam employee = request.getEmployee();
            employee.setProjectRole(request.getProjectRole());
            employee.setName(request.getEmployee().getName());
            employee.setParty_email(request.getEmployee().getParty_email());
            employee.setParty_phone(request.getEmployee().getParty_phone());
            employee.setProject(project);
            project.getProjectTeam().add(employee);
            projectRepository.save(project);

        }
        else{
            throw new IllegalArgumentException("Project Not found");
        }

    }

    @Override
    public List<TeamRetrieval> getProjectTeamMembers(UUID projectId) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new IllegalArgumentException("Project Not Found"));

        return project.getProjectTeam().stream()
                .map(this::mapToTeamRetrieval)
                .collect(Collectors.toList());

    }

    @Override
    public void updateProjectRole(UUID projectId, UUID memberId, String newRole) {
        projectTeamRepository.findById(memberId)
                .orElseThrow(()-> new EntityNotFoundException("Project Team member not found"))
                .setProjectRole(ProjectRole.valueOf(newRole));

    }

    @Override
    @Transactional
        public void removeMemberFromProject(UUID projectId, UUID memberId) {
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new EntityNotFoundException("Project Not Found"));


            ProjectTeam memberToRemove = project.getProjectTeam().stream()
                    .filter(member -> member.getId().equals(memberId))
                    .findFirst()
                    .orElseThrow(() -> new EntityNotFoundException("Team member not found in project"));

            project.getProjectTeam().remove(memberToRemove);
            projectRepository.save(project);
        }





    private boolean isEmployeeAlreadyExists(Project project, ProjectTeam employee){
        return project.getProjectTeam().stream()
                .anyMatch(existingEmployee -> existingEmployee.getName().equals(employee.getName()));
    }

    private TeamRetrieval mapToTeamRetrieval(ProjectTeam projectTeam){
        return TeamRetrieval.builder()
                .id(projectTeam.getId())
                .name(projectTeam.getName())
                .party_email(projectTeam.getParty_email())
                .party_phone(projectTeam.getParty_phone())
                .companyRole(projectTeam.getCompanyRole())
                .projectRole(projectTeam.getProjectRole()).build();
    }
}

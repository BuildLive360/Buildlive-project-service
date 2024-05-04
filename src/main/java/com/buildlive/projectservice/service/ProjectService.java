package com.buildlive.projectservice.service;

import com.buildlive.projectservice.dto.ProjectDto;
import com.buildlive.projectservice.dto.ProjectResponse;
import com.buildlive.projectservice.dto.ProjectTeamDto;
import com.buildlive.projectservice.dto.TeamRetrieval;
import com.buildlive.projectservice.entity.Project;
import com.buildlive.projectservice.entity.ProjectMaterial;

import java.util.List;
import java.util.UUID;

public interface ProjectService {

    ProjectResponse createProject(ProjectDto request);

    List<Project> getAllProjectsOfACompanyForUser(UUID companyId, String userEmail, UUID userId);



    void addEmployeeToProjectTeam(ProjectTeamDto request);

    List<TeamRetrieval> getProjectTeamMembers(UUID projectId);

    void updateProjectRole(UUID projectId,UUID memberId,String newRole);

    void removeMemberFromProject(UUID projectId,UUID memberId);

    List<ProjectMaterial> getAllProjectMaterials(UUID projectId);


}

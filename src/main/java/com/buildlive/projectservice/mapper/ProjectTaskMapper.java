package com.buildlive.projectservice.mapper;

import com.buildlive.projectservice.dto.ProjectDto;
import com.buildlive.projectservice.dto.ProjectTaskDto;
import com.buildlive.projectservice.dto.ProjectTeamDto;
import com.buildlive.projectservice.dto.task.ProjectResponseDTO;
import com.buildlive.projectservice.dto.task.ProjectTaskResponseDTO;
import com.buildlive.projectservice.dto.task.ProjectTeamResponseDTO;
import com.buildlive.projectservice.entity.ProjectTasks;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectTaskMapper {


    public ProjectTaskResponseDTO toDTO(ProjectTasks projectTasks) {
        return new ProjectTaskResponseDTO(
                projectTasks.getId(),
                projectTasks.getTaskName(),
                projectTasks.getStartDate(),
                projectTasks.getEndDate(),
                new ProjectTeamResponseDTO(
                        projectTasks.getProjectTeam().getId(),
                        projectTasks.getProjectTeam().getName(),
                        projectTasks.getProjectTeam().getProjectRole()

                ),
                new ProjectResponseDTO(
                        projectTasks.getProject().getId(),
                        projectTasks.getProject().getProjectName(),
                        projectTasks.getProject().getAddress(),
                        projectTasks.getProject().getCity(),
                        projectTasks.getProject().getStart_date(),
                        projectTasks.getProject().getEnd_date(),
                        projectTasks.getProject().getProject_value(),
                        projectTasks.getProject().getStatus(),
                        projectTasks.getProject().getCreator(),
                        projectTasks.getProject().getCompany()
                ),
                projectTasks.getTaskStatus()
        );
    }

    public List<ProjectTaskResponseDTO> toDTOs(List<ProjectTasks> projectTasksList) {
        return projectTasksList.stream().map(this::toDTO).collect(Collectors.toList());
    }
}

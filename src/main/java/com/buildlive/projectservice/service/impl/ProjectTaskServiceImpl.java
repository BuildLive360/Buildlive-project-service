package com.buildlive.projectservice.service.impl;

import com.buildlive.projectservice.dto.ProjectTaskDto;
import com.buildlive.projectservice.entity.Project;
import com.buildlive.projectservice.entity.ProjectTasks;
import com.buildlive.projectservice.entity.ProjectTeam;
import com.buildlive.projectservice.entity.TaskStatus;
import com.buildlive.projectservice.repo.IProjectTaskRepository;
import com.buildlive.projectservice.repo.ProjectRepository;
import com.buildlive.projectservice.repo.ProjectTeamRepository;
import com.buildlive.projectservice.service.IProjectTaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectTaskServiceImpl implements IProjectTaskService {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectTeamRepository projectTeamRepository;

    @Autowired
    IProjectTaskRepository projectTaskRepository;

    @Override
    public ProjectTasks createTask(ProjectTaskDto taskDto) {
        Project project = projectRepository.findById(taskDto.getProjectId()).orElseThrow(
                ()-> new EntityNotFoundException("Project Not Found")
        );

        ProjectTeam projectTeam = projectTeamRepository.findById(taskDto.getAssignedTo()).orElseThrow(
                ()-> new EntityNotFoundException("Team member Not Found"));


        ProjectTasks projectTasks = ProjectTasks.builder()
                .taskName(taskDto.getTaskName())
                .projectTeam(projectTeam)
                .startDate(taskDto.getStartDate())
                .endDate(taskDto.getEndDate())
                .taskStatus(TaskStatus.NOT_STARTED)
                .project(project).build();

        project.getProjectTasks().add(projectTasks);
        return  projectTaskRepository.save(projectTasks);

    }

    @Override
    public List<ProjectTasks> getAllProjectTasks(UUID projectId, String partyEmail) {
        return projectTaskRepository.findByProjectIdAndProjectTeamPartyEmail(projectId,partyEmail);
    }
}

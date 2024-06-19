package com.buildlive.projectservice.service.impl;

import com.buildlive.projectservice.dto.ProjectTaskDto;
import com.buildlive.projectservice.dto.task.TaskUpdationRequest;
import com.buildlive.projectservice.entity.*;
import com.buildlive.projectservice.repo.IProjectTaskRepository;
import com.buildlive.projectservice.repo.ProjectRepository;
import com.buildlive.projectservice.repo.ProjectTeamRepository;
import com.buildlive.projectservice.service.IProjectTaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                .taskStatus(TaskStatus.COMPLETED)
                .project(project).build();

        project.getProjectTasks().add(projectTasks);
        return  projectTaskRepository.save(projectTasks);

    }

    @Override
    public List<ProjectTasks> getAllProjectTasks(UUID projectId, String partyEmail) {
        Optional<ProjectTeam> projectTeam = projectTeamRepository.findByPartyEmail(partyEmail);

        if (projectTeam.isPresent()){
            ProjectTeam member = projectTeam.get();

            if   (  member.getProjectRole().equals(ProjectRole.MANAGER)
                    || member.getProjectRole().equals(ProjectRole.ADMIN)){

                return projectTaskRepository.findByProjectId(projectId);
            }
            else {
                return projectTaskRepository.findByProjectIdAndProjectTeamPartyEmail(projectId,partyEmail);
            }
        }
        else {
            throw new RuntimeException("Unwanted member");
        }
    }

    @Override
    public ProjectTasks getTaskDetails(UUID id) {

        Optional<ProjectTasks> optionalProjectTask = projectTaskRepository.findById(id);
        if (optionalProjectTask.isPresent()){
            return optionalProjectTask.get();
        }
        else {
            throw new EntityNotFoundException("Task Not found");
        }
    }

    @Override
    public void updateTask(TaskUpdationRequest taskUpdationRequest,UUID taskId) {

       ProjectTasks task =  projectTaskRepository.findById(taskId)
                        .orElseThrow(()->new EntityNotFoundException("Task Not found"));
                                task.setTaskStatus(TaskStatus.valueOf(taskUpdationRequest.getTaskStatus()));
               projectTaskRepository.save(task);
    }
}

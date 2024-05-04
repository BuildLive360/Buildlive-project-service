package com.buildlive.projectservice.controller;

import com.buildlive.projectservice.entity.Project;
import com.buildlive.projectservice.entity.ProjectRole;
import com.buildlive.projectservice.entity.ProjectTeam;
import com.buildlive.projectservice.repo.ProjectRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/project")
@RestController
public class ProjectTeamController {

    @Autowired
    ProjectRepository projectRepository;


    @GetMapping("/{projectId}/project-team/{partyEmail}")
    public ResponseEntity<?> getProjectUserRole(
            @PathVariable(name = "projectId")UUID projectId,
            @PathVariable(name = "partyEmail") String partyEmail
            ){

        Project project = projectRepository.findById(projectId).get();
        if(project!=null){
            Optional<ProjectTeam> projectTeamOptional = project.getProjectTeam().stream()
                    .filter(teamMember -> teamMember.getParty_email().equals(partyEmail))
                    .findFirst();

            if (projectTeamOptional.isPresent()) {
                ProjectRole projectRole = projectTeamOptional.get().getProjectRole();
                return ResponseEntity.ok(projectRole);
            }
            else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
       }

}

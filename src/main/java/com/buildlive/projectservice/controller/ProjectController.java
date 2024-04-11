package com.buildlive.projectservice.controller;

import com.buildlive.projectservice.dto.*;
import com.buildlive.projectservice.entity.Project;
import com.buildlive.projectservice.service.ProjectService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/project")
@RestController
public class ProjectController {

    @Autowired
    ProjectService projectService;

//    @GetMapping
//    public String demoApi(){
//        return "This is a demo";
//    }

    @PostMapping("/create")
    public ResponseEntity<ProjectResponse> createProject(@RequestBody ProjectDto projectDto) {
        ProjectResponse response = projectService.createProject(projectDto);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


//    @GetMapping("/")
//    public ResponseEntity<?> getAllProjectsOfACompany(@PathVariable UUID companyId){
//
//    }

    @GetMapping("/{companyId}/company")
    public ResponseEntity<List<Project>> getAllProjectsOfACompany(@PathVariable UUID companyId){
        return ResponseEntity.ok(projectService.getAllByCompany(companyId));
    }

    @PostMapping("/addToProject")
    public ResponseEntity<?> addMemberToProject(@RequestBody ProjectTeamDto request){
        try {
            projectService.addEmployeeToProjectTeam(request);
            return ResponseEntity.ok().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{projectId}/team-members")
    public ResponseEntity<List<TeamRetrieval>> getProjectTeamMembers(@PathVariable(name = "projectId") UUID projectId){
        return ResponseEntity.ok(projectService.getProjectTeamMembers(projectId));
    }

    @PutMapping("/{projectId}/team-members/{memberId}/update-role")
    public ResponseEntity<?> updateProjectRole(
            @PathVariable UUID projectId,
            @PathVariable UUID memberId,
            @RequestBody UpdateProjectRoleRequest request
            ){
        try {
            projectService.updateProjectRole(projectId,
                                             memberId,request.getRole());
            return ResponseEntity.ok().build();
        }
        catch (EntityNotFoundException ex){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }


    @DeleteMapping("/{projectId}/team-members/{memberId}/remove")
    public ResponseEntity<?> removeMemberFromProject(
            @PathVariable(name = "projectId") UUID projectId,
            @PathVariable(name = "memberId") UUID memberId){


        try{

            System.out.println(projectId);
            projectService.removeMemberFromProject(projectId,memberId);
            return ResponseEntity.noContent().build();
        }
        catch (EntityNotFoundException e){
            System.out.println("llll");
            return ResponseEntity.notFound().build();
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}

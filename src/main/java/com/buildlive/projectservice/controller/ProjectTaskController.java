package com.buildlive.projectservice.controller;

import com.buildlive.projectservice.dto.ProjectTaskDto;
import com.buildlive.projectservice.dto.UpdateProjectRoleRequest;
import com.buildlive.projectservice.dto.task.ProjectTaskResponseDTO;
import com.buildlive.projectservice.dto.task.TaskUpdationRequest;
import com.buildlive.projectservice.entity.ProjectTasks;
import com.buildlive.projectservice.entity.ProjectTeam;
import com.buildlive.projectservice.mapper.ProjectTaskMapper;
import com.buildlive.projectservice.service.IProjectTaskService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@RequestMapping("/api/v1/project")
@RestController
public class ProjectTaskController {

    @Autowired
    private ProjectTaskMapper projectTaskMapper;


    @Autowired
    IProjectTaskService projectTaskService;
    @PostMapping("tasks/create-task")
    public ResponseEntity<?> createNewTask(@RequestBody ProjectTaskDto projectTaskDto){
       return ResponseEntity.ok(projectTaskService.createTask(projectTaskDto));
    }

    @GetMapping("/tasks/getAll-projectTasks")
    public ResponseEntity<List<ProjectTaskResponseDTO>> getAllProjectTasks(
            @RequestParam("projectId") String projectId,
            @RequestParam("partyEmail") String partyEmail){


        List<ProjectTasks> tasks = projectTaskService.getAllProjectTasks(UUID.fromString(projectId), partyEmail);
        List<ProjectTaskResponseDTO> taskDTOs = tasks.stream()
                .map(projectTaskMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(taskDTOs);
    }


    @GetMapping("/tasks/get-details/{taskId}")
    public ResponseEntity<?> getTaskDetails(
            @PathVariable(name = "taskId") UUID id
    ){
        return ResponseEntity.ok(projectTaskService.getTaskDetails(id));
    }

    @PutMapping("/tasks/update-status/{taskId}")
    public ResponseEntity<?> updateTaskStatus(@RequestBody TaskUpdationRequest request,
                                              @PathVariable(name = "taskId") UUID taskId){


        try {
            System.out.println(taskId+request.getTaskStatus());
            projectTaskService.updateTask(request,taskId);
            return ResponseEntity.ok().build();
        }
        catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }




}

package com.buildlive.projectservice.controller;

import com.buildlive.projectservice.dto.ProjectTaskDto;
import com.buildlive.projectservice.entity.ProjectTasks;
import com.buildlive.projectservice.service.IProjectTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RequestMapping("/api/v1/project")
@RestController
public class ProjectTaskController {


    @Autowired
    IProjectTaskService projectTaskService;
    @PostMapping("tasks/create-task")
    public ResponseEntity<?> createNewTask(@RequestBody ProjectTaskDto projectTaskDto){
       return ResponseEntity.ok(projectTaskService.createTask(projectTaskDto));
    }

    @GetMapping("/tasks/getAll-projectTasks")
    public ResponseEntity<List<ProjectTasks>> getAllProjectTasks(
            @RequestParam("projectId") String projectId,
            @RequestParam("partyEmail") String partyEmail){

      return  ResponseEntity.ok(projectTaskService.getAllProjectTasks(UUID.fromString(projectId),partyEmail));
    }


}

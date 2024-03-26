package com.buildlive.projectservice.controller;

import com.buildlive.projectservice.dto.ProjectDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/project")
@RestController
public class ProjectController {


    @GetMapping
    public String demoApi(){
        return "This is a demo";
    }

    @PostMapping("/create")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto projectDto){

    }
}

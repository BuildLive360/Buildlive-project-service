package com.buildlive.projectservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/project")
@RestController
public class ProjectController {


    @GetMapping
    public String demoApi(){
        return "This is a demo";
    }
}

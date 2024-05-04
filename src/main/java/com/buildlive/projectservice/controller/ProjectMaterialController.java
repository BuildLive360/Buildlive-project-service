package com.buildlive.projectservice.controller;

import com.buildlive.projectservice.dto.ErrorResponse;
import com.buildlive.projectservice.dto.ProjectMaterialDto;
import com.buildlive.projectservice.dto.ProjectUsedMaterialDto;
import com.buildlive.projectservice.entity.ProjectMaterial;
import com.buildlive.projectservice.entity.ProjectMaterialEntryList;
import com.buildlive.projectservice.service.IProjectMaterialService;
import com.buildlive.projectservice.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/project")
@RestController
public class ProjectMaterialController {

    @Autowired
    IProjectMaterialService projectMaterialService;

    @Autowired
    ProjectService projectService;

    @PostMapping("/materials/received-materials")
    public ResponseEntity<?> receiveMaterial(@RequestBody ProjectMaterialDto request) {
        try {
            projectMaterialService.saveProjectReceivedMaterial(request);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid request: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An unexpected error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/{projectId}/materials/project-materials")
    public ResponseEntity<List<ProjectMaterial>> getAllProjectMaterials(@PathVariable(name = "projectId")UUID projectId){
        try {
            List<ProjectMaterial> projectMaterials = projectService.getAllProjectMaterials(projectId);
            return ResponseEntity.ok().body(projectMaterials);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/materials/projectMaterial-entries")
    public ResponseEntity<List<ProjectMaterialEntryList>> getMaterialEntries(
                                        @RequestParam UUID materialId
                                     ){
            return ResponseEntity.ok().body(projectMaterialService.getMaterialEntries(materialId));
    }

    @PostMapping("/materials/used-materials")
    public ResponseEntity<?> usedMaterial(@RequestBody ProjectUsedMaterialDto request){

        try {
            projectMaterialService.saveProjectUsedMaterial(request);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Invalid request: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("An unexpected error occurred: " + e.getMessage()));
        }
    }

}

package com.buildlive.projectservice.service.impl;

import com.buildlive.projectservice.dto.ProjectMaterialDto;
import com.buildlive.projectservice.dto.ProjectUsedMaterialDto;
import com.buildlive.projectservice.entity.MaterialType;
import com.buildlive.projectservice.entity.Project;
import com.buildlive.projectservice.entity.ProjectMaterial;
import com.buildlive.projectservice.entity.ProjectMaterialEntryList;
import com.buildlive.projectservice.repo.IProjectMaterialEntryListRepository;
import com.buildlive.projectservice.repo.ProjectMaterialRepository;
import com.buildlive.projectservice.repo.ProjectRepository;
import com.buildlive.projectservice.service.IProjectMaterialService;
import com.buildlive.projectservice.service.feign.CompanyFeignClient;
import com.netflix.discovery.converters.Auto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectMaterialServiceImpl implements IProjectMaterialService {


    private final ProjectRepository projectRepository;
    private final ProjectMaterialRepository projectMaterialRepository;
    private final CompanyFeignClient companyFeignClient;
    private final IProjectMaterialEntryListRepository projectMaterialEntryListRepository;

    @Override
    public void saveProjectReceivedMaterial(ProjectMaterialDto request) {

        UUID projectId = request.getProjectId();
        Project project = projectRepository.findById(projectId).orElseThrow(() ->
                new IllegalArgumentException("Project not found"));

        UUID companyId = project.getCompany();
        String entryPerson = companyFeignClient.getPartyMemberName(request.getUserEmail(), companyId);

        ProjectMaterial existingMaterial = project.getProjectMaterials().stream()
                .filter(material -> material.getMaterialName().equals(request.getMaterial()))
                .findFirst()
                .orElse(null);

        if (existingMaterial != null) {

            ProjectMaterialEntryList entryList = ProjectMaterialEntryList.builder()
                    .entryPerson(entryPerson)
                    .quantity(request.getQuantity())
                    .entryTime(LocalDateTime.now())
                    .projectMaterial(existingMaterial)
                    .materialType(MaterialType.RECEIVED)
                    .build();

            if (existingMaterial.getTransactions() == null) {
                existingMaterial.setTransactions(new ArrayList<>());
            }
            existingMaterial.getTransactions().add(entryList);

            // If the material already exists, update its quantities
            existingMaterial.setQuantity(existingMaterial.getQuantity() + request.getQuantity());
            existingMaterial.setCurrentStock(existingMaterial.getCurrentStock() + request.getQuantity());
            existingMaterial.setReceivedStock(existingMaterial.getReceivedStock() + request.getQuantity());
            existingMaterial.setEntryPerson(entryPerson);
            existingMaterial.setLocalDateTime(LocalDateTime.now());
        } else {
            // If the material does not exist, create a new material entry
            ProjectMaterial newMaterial = ProjectMaterial.builder()
                    .partyMember(request.getPartyMember())
                    .entryPerson(entryPerson)
                    .materialName(request.getMaterial())
                    .quantity(request.getQuantity())
                    .currentStock(request.getQuantity())
                    .receivedStock(request.getQuantity())
                    .localDateTime(LocalDateTime.now())
                    .project(project)
                    .build();

            // Add the new material to the project's list of materials
            project.getProjectMaterials().add(newMaterial);

            if (newMaterial.getTransactions() == null) {
                newMaterial.setTransactions(new ArrayList<>());
            }

            ProjectMaterialEntryList entryList = ProjectMaterialEntryList.builder()
                    .entryPerson(entryPerson)
                    .quantity(request.getQuantity())
                    .entryTime(LocalDateTime.now())
                    .projectMaterial(newMaterial)
                    .materialType(MaterialType.RECEIVED)
                    .build();

            newMaterial.getTransactions().add(entryList);
            projectMaterialRepository.save(newMaterial);
        }

        // Save the updated project entity
        projectRepository.save(project);
    }

    @Override
    public void saveProjectUsedMaterial(ProjectUsedMaterialDto request) {

        UUID projectId = request.getProjectId();

        Project project = projectRepository.findById(projectId).orElseThrow(() ->
                new EntityNotFoundException("Project not found"));
        UUID companyId = project.getCompany();
        String entryPerson = companyFeignClient.getPartyMemberName(request.getUserEmail(), companyId);
        ProjectMaterial projectMaterial = project.getProjectMaterials().stream()
                .filter(material -> material.getMaterialName().equals(request.getMaterial()) && material.getId().equals(request.getMaterialId()))
                .findFirst()
                .orElse(null);

        if(projectMaterial!=null){
            ProjectMaterialEntryList entryList = ProjectMaterialEntryList.builder()
                    .entryPerson(entryPerson)
                    .quantity(request.getQuantity())
                    .projectMaterial(projectMaterial)
                    .entryTime(LocalDateTime.now())
                    .materialType(MaterialType.USED)
                    .build();

            projectMaterial.getTransactions().add(entryList);
            projectMaterial.setCurrentStock(projectMaterial.getCurrentStock() - request.getQuantity());
            projectMaterial.setEntryPerson(entryPerson);
            projectMaterial.setUsedDescription(request.getDescription());
            projectMaterial.setLocalDateTime(LocalDateTime.now());
            ProjectMaterial projectMaterial1 = projectMaterialRepository.save(projectMaterial);
            projectMaterial1.setUsedStock(projectMaterial1.getReceivedStock() - projectMaterial1.getCurrentStock());
            projectMaterialRepository.save(projectMaterial1);
        }


    }

    @Override
    public List<ProjectMaterialEntryList> getMaterialEntries(UUID projectMaterialId) {
        return projectMaterialEntryListRepository.findByProjectMaterialId(projectMaterialId);
    }
}

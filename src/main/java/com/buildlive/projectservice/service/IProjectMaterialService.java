package com.buildlive.projectservice.service;

import com.buildlive.projectservice.dto.ProjectMaterialDto;
import com.buildlive.projectservice.dto.ProjectUsedMaterialDto;
import com.buildlive.projectservice.entity.ProjectMaterialEntryList;

import java.util.List;
import java.util.UUID;

public interface IProjectMaterialService {

    void saveProjectReceivedMaterial(ProjectMaterialDto request);
    void saveProjectUsedMaterial(ProjectUsedMaterialDto request);

    List<ProjectMaterialEntryList> getMaterialEntries(UUID projectMaterialId);
}

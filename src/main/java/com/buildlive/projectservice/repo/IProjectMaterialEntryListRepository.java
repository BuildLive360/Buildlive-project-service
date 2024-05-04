package com.buildlive.projectservice.repo;

import com.buildlive.projectservice.entity.ProjectMaterialEntryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IProjectMaterialEntryListRepository extends JpaRepository<ProjectMaterialEntryList, UUID> {
    List<ProjectMaterialEntryList> findByProjectMaterialId(UUID materialId);
}

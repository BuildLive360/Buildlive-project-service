package com.buildlive.projectservice.repo;

import com.buildlive.projectservice.entity.ProjectMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectMaterialRepository extends JpaRepository<ProjectMaterial, UUID> {
}

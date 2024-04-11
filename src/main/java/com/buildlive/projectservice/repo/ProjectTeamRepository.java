package com.buildlive.projectservice.repo;

import com.buildlive.projectservice.entity.ProjectTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProjectTeamRepository extends JpaRepository<ProjectTeam, UUID> {
}

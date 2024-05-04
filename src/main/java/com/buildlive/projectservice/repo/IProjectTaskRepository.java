package com.buildlive.projectservice.repo;

import com.buildlive.projectservice.entity.ProjectTasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IProjectTaskRepository extends JpaRepository<ProjectTasks, UUID> {

    @Query("SELECT pt FROM ProjectTasks pt JOIN pt.projectTeam ptm WHERE pt.project.id = :projectId AND ptm.party_email = :partyEmail")
    List<ProjectTasks> findByProjectIdAndProjectTeamPartyEmail(@Param("projectId") UUID projectId, @Param("partyEmail") String partyEmail);
}


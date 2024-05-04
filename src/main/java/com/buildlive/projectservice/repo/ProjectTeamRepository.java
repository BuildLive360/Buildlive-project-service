package com.buildlive.projectservice.repo;

import com.buildlive.projectservice.entity.ProjectTeam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectTeamRepository extends JpaRepository<ProjectTeam, UUID> {

    @Query("SELECT p FROM ProjectTeam p WHERE p.party_email = :partyEmail")
    Optional<ProjectTeam> findByPartyEmail(String partyEmail);
}

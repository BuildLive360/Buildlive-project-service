package com.buildlive.projectservice.repo;

import com.buildlive.projectservice.entity.ProjectAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface IProjectAttendanceRepository extends JpaRepository<ProjectAttendance, UUID> {

    List<ProjectAttendance> findByCompanyIdAndProjectIdAndDate(UUID companyId, UUID projectId, Date date);
}

package com.buildlive.projectservice.repo;

import com.buildlive.projectservice.entity.AttendanceWorkforce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IAttendanceWorkforceRepository extends JpaRepository<AttendanceWorkforce, UUID> {

    List<AttendanceWorkforce> findByAttendanceId(UUID attendanceId);
}

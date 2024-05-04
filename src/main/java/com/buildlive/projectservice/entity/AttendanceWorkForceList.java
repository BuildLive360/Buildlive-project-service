//package com.buildlive.projectservice.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//import java.util.UUID;
//
//@Entity
//@Table
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class AttendanceWorkForceList {
//
//    @Id
//    private UUID id;
//    private String workForceName;
//    private Long salaryPerShift;
//    private Long workerCount;
//    private Long presentCount;
//    private Long absentCount;
//    private LocalDate date;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "attendance")
//    @JsonIgnore
//    private ProjectAttendanceEntry attendanceEntry;
//}

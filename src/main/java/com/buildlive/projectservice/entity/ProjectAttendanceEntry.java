//package com.buildlive.projectservice.entity;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@Entity
//@Table
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class ProjectAttendanceEntry {
//
//    @Id
//    private UUID id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "entryPerson")
//    @JsonIgnore
//    private ProjectTeam entryPerson;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "project")
//    @JsonIgnore
//    private Project project;
//
//    private LocalDate date;
//
//
//
//    @Enumerated(EnumType.STRING)
//    private Attendance attendance;
//
//    @OneToMany(mappedBy = "attendanceEntryWorkforceList",cascade = CascadeType.ALL,orphanRemoval = true)
//    @JsonIgnoreProperties("attendanceEntryWorkforceList")
//    List<AttendanceWorkForceList> workForceLists = new ArrayList<>();
//
//
//
//}

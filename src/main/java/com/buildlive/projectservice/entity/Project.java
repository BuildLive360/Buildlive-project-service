package com.buildlive.projectservice.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String projectName;
    private String address;
    private String city;
    private LocalDate start_date;
    private LocalDate end_date;
    private String project_value;

    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    private UUID creator;
    private UUID company;
    private LocalDateTime createdTime;



    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties("project")
    List<ProjectTeam> projectTeam = new ArrayList<>();


    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties("project")
    List<ProjectMaterial> projectMaterials = new ArrayList<>();


    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties("project")
    List<ProjectTasks> projectTasks = new ArrayList<>();

//    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
//    @JsonIgnoreProperties("project")
//    List<ProjectAttendanceEntry> attendanceEntries = new ArrayList<>();


}

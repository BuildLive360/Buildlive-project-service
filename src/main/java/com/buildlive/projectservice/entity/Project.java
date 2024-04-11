package com.buildlive.projectservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true)
    List<ProjectTeam> projectTeam = new ArrayList<>();
}

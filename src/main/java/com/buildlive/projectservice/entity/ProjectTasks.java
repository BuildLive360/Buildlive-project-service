package com.buildlive.projectservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Member")
    @JsonIgnore
    private ProjectTeam projectTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project")
    @JsonIgnore
    private Project project;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
}

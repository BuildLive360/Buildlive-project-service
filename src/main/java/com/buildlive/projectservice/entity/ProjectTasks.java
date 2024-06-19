package com.buildlive.projectservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
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

    @ManyToOne
    @JoinColumn(name = "Member")
    private ProjectTeam projectTeam;

    @ManyToOne
    @JoinColumn(name = "project")
    private Project project;

    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;
}

package com.buildlive.projectservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String projectName;
    private String address;
    private LocalDate start_date;
    private LocalDate end_date;
    private String project_value;
    private ProjectStatus status;
    private UUID creator;
    private UUID company;
}

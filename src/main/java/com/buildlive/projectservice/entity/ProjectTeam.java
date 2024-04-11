package com.buildlive.projectservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String party_email;
    private String party_phone;
    private String companyRole;

    @Enumerated(EnumType.STRING)
    private ProjectRole projectRole;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "project")
    private Project project;

}

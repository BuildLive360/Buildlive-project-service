package com.buildlive.projectservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMaterialEntryList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String entryPerson;
    private Double quantity;
    private LocalDateTime entryTime;

    @ManyToOne
    @JoinColumn(name = "material_id")
    private ProjectMaterial projectMaterial;



    @Enumerated(EnumType.STRING)
    private MaterialType materialType;
}

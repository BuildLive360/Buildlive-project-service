package com.buildlive.projectservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProjectMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String partyMember;

    private String entryPerson;

    @Column(name = "material")
    private String materialName;


    private Double quantity;
    private Double currentStock;
    private Double receivedStock;
    private Double usedStock;
    private String usedDescription;
    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "project")
    @JsonIgnore
    private Project project;

    @OneToMany(mappedBy = "projectMaterial", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectMaterialEntryList> transactions = new ArrayList<>();
}

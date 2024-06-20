package com.buildlive.projectservice.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project")
    @JsonIgnore
    private Project project;

    @OneToMany(mappedBy = "projectTeam",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnoreProperties("projectTeam")
    @Builder.Default
    List<ProjectTasks> projectTasks = new ArrayList<>();

//    @OneToMany(mappedBy = "projectTeam",cascade = CascadeType.ALL)
//    @JsonIgnoreProperties("projectTeam")
//    List<ProjectAttendanceEntry> attendanceEntryList = new ArrayList<>();

}

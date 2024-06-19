package com.buildlive.projectservice.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID projectId;
    private UUID companyId;
    private UUID teamMemberId;

    @OneToMany(mappedBy = "attendance", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<AttendanceWorkforce> workforceAllocations = new HashSet<>();
    private Long totalSalary;

    @Temporal(TemporalType.DATE)
    private Date date;

}

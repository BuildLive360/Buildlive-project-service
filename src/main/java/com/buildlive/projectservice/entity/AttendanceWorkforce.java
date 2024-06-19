package com.buildlive.projectservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "attendance_workforce")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceWorkforce {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id")
    @JsonBackReference
    private ProjectAttendance attendance;

    private UUID workforceId;
    private Integer quantity;
}

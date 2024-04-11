package com.buildlive.projectservice.dto;

import com.buildlive.projectservice.entity.ProjectRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeamRetrieval {

    private UUID id;
    private String name;
    private String party_email;
    private String party_phone;
    private String companyRole;
    private ProjectRole projectRole;
}

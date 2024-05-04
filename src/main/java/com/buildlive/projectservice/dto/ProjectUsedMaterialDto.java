package com.buildlive.projectservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUsedMaterialDto {


    private UUID materialId;
    private String material;
    private Double quantity;
    private UUID projectId;
    private String userEmail;
    private String description;

}

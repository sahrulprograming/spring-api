package com.example.officebookingsystem.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingUpdateRequest {
    private String name;
    private String address;
    private Long idComplex;
    private String description;
    private String buildingImage;
    private String imageType;
    private List<FacilityRequestUpdate> facilities;

}

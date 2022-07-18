package com.example.officebookingsystem.domain.dto.request;

import com.example.officebookingsystem.domain.entity.Facility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuildingRequest {
    private String name;
    private String address;
    private Long idComplex;
    private String description;
    private String buildingImage;
    private String imageType;
    private List <FacilityCreateRequest> facilities;

}

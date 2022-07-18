package com.example.officebookingsystem.domain.dto.response;

import lombok.Data;

import javax.persistence.Lob;
import java.util.List;

@Data
public class BuildingResponse {
    private Long id;
    private String buildingName;
    private String complexName;
    private String Address;
    private Integer numOfRooms;
    private String Description;
    private List <FacilityResponse> facilityResponseList;

    @Lob
    private String base64Image;

    private String imageType;
}

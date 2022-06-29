package com.example.officebookingsystem.domain.dto.response;

import lombok.Data;

@Data
public class BuildingResponse {
    private Long id;
    private String buildingName;
    private String complexName;
    private String complexAdress;
    private Integer numOfRooms;
    private String Description;
}

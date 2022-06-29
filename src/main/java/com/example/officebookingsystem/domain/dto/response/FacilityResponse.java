package com.example.officebookingsystem.domain.dto.response;

import lombok.Data;

@Data
public class FacilityResponse {
    private String name;
    private Float distance;
    private Integer duration;
    private String building_name;
}

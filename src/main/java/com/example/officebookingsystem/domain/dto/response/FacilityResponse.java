package com.example.officebookingsystem.domain.dto.response;

import lombok.Data;

@Data
public class FacilityResponse {
    private String name;
    private Float distance;
    private Integer duration;
    private Long categoryId;
    private Long facilityId;
}

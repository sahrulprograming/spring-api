package com.example.officebookingsystem.domain.dto.request;

import lombok.Data;

@Data
public class FacilityCreateRequest {
    private String name;
    private String description;
    private Long building_id;
}

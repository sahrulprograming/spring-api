package com.example.officebookingsystem.domain.dto.request;

import lombok.Data;

@Data
public class BuildingRequest {
    private Long complex_id;
    private String building_name;
    private String address;
    private String description;
}

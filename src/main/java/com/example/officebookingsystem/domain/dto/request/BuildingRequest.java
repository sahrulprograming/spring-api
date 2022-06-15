package com.example.officebookingsystem.domain.dto.request;

import lombok.Data;

@Data
public class BuildingRequest {
    private String name;
    private String address;
    private Long idComplex;
    private String description;
}

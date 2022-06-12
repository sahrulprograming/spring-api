package com.example.officebookingsystem.domain.dto.request;

import lombok.Data;

@Data
public class BuildingRequest {
    private Long idComplex;
    private String name;
    private String address;
    private String description;
}

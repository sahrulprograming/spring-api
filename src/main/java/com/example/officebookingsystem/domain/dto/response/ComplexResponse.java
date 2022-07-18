package com.example.officebookingsystem.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexResponse {
    private Long id;
    private String complex_name;
    private String address;
    private String city_name;
    private String district_name;
    private Integer numOfBuilding;
}

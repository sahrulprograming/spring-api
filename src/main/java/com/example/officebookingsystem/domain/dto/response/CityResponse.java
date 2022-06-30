package com.example.officebookingsystem.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CityResponse {
    private String name;
    private Long id;
    private Long province_id;


}

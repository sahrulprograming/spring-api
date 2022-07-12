package com.example.officebookingsystem.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplexCreateRequest {
    private String complex_name;
    private String street;
    private Long province_id;;
    private Long city_id;
    private Long district_id;
}

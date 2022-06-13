package com.example.officebookingsystem.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictResponse {
    private Long district_id;
    private String name;
    private Long ciy_id;
    private String city_name;
}

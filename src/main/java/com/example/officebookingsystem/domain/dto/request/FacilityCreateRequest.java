package com.example.officebookingsystem.domain.dto.request;


import lombok.Data;

@Data
public class FacilityCreateRequest {
    private String name;
    private Long building_category_id;
    private Long building_id;
    private Float distance;
    private Integer duration;
}

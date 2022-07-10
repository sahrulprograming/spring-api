package com.example.officebookingsystem.domain.dto.request;


import lombok.Data;

@Data
public class FacilityCreateRequest {
    private String facility_name;
    private Long facility_category_id;
    private Float distance;
    private Integer duration;
}

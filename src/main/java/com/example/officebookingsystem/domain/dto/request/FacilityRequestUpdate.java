package com.example.officebookingsystem.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacilityRequestUpdate {
    private String facility_name;
    private Long facility_category_id;
    private Float distance;
    private Integer duration;
    private Long facilityId;
}

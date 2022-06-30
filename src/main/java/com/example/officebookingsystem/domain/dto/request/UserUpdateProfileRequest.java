package com.example.officebookingsystem.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateProfileRequest {
    private String name;
    private String email;
    private Long province_id;
    private Long city_id;
    private Long district_id;
}

package com.example.officebookingsystem.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictCreateRequest {

    @NotBlank
    private String name;

    private Long city_id;

}

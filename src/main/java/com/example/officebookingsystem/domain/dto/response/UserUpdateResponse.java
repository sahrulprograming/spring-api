package com.example.officebookingsystem.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateResponse {
    private Long userDetailID;
    private String name;
    private String username;
    private String email;
    private String province;
    private String city;
    private String district;


}

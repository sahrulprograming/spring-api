package com.example.officebookingsystem.controller;

import com.example.officebookingsystem.domain.dto.request.ComplexCreateRequest;
import com.example.officebookingsystem.domain.dto.request.UserUpdateProfileRequest;
import com.example.officebookingsystem.domain.entity.Complex;
import com.example.officebookingsystem.service.DistrictService;
import com.example.officebookingsystem.service.UpdateUserProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/page/user")
@Api(tags = "User Update Profile API", description = "Profiles")
public class UserUpdateProfileController {

    @Autowired
    private UpdateUserProfileService userProfileService;

    @Autowired
    private DistrictService districtService;

    @GetMapping("/district/list/{id}")
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "List District", notes = "Endpoint for listing all Districts by City")
    public ResponseEntity<?> listDistrictByCityId(@PathVariable("id") Long id){
        return districtService.listDistrictByCity(id);
    }


    @PutMapping("/email/update/{id}")
    @ApiOperation(value = "Update Email ", notes = "Endpoint for update profile by id ")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> editProfile(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateProfileRequest userUpdateProfileRequest){
        return userProfileService.editUserProfile(id, userUpdateProfileRequest);
    }
}

package com.example.officebookingsystem.controller;
import com.example.officebookingsystem.domain.dto.request.UploadProfileImageRequest;
import com.example.officebookingsystem.domain.dto.request.UserUpdateProfileRequest;
import com.example.officebookingsystem.domain.entity.Province;
import com.example.officebookingsystem.service.CityService;
import com.example.officebookingsystem.service.DistrictService;
import com.example.officebookingsystem.service.ProvinceService;
import com.example.officebookingsystem.service.UpdateUserProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/page/user")
@Api(tags = "User Update Profile API", description = "Profiles")
public class UserUpdateProfileController {

    @Autowired
    private UpdateUserProfileService userProfileService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ProvinceService provinceService;

    @GetMapping("/province/findAll")
    @ApiOperation(value = "Get All Provinces", notes = "Endpoint for admin ger All Provinces")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Province>> listAllProvince(){
        return provinceService.listAllProvince();
    }


    @GetMapping("/district/list/{id}")
    @PreAuthorize("hasRole('USER')")
    @ApiOperation(value = "List District", notes = "Endpoint for listing all Districts by City")
    public ResponseEntity<?> listDistrictByCityId(@PathVariable("id") Long id){
        return districtService.listDistrictByCity(id);
    }

    @GetMapping("/city/list/{id}")
    @ApiOperation(value = "List Cities by Province", notes = "Endpoint for listing all cities by province")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> listCityByProvinceId(@PathVariable("id") Long id) {
        return cityService.listCityByProvinceId(id);
    }

    @PutMapping("/profile/update/{id}")
    @ApiOperation(value = "Update Profile ", notes = "Endpoint for update profile by User id ")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> editProfile(@PathVariable("id") Long id, @Valid @RequestBody UserUpdateProfileRequest userUpdateProfileRequest){
        return userProfileService.editUserProfile(id, userUpdateProfileRequest);
    }

    @PostMapping("/profile/image/{id}")
    @ApiOperation(value = "Upload Image ", notes = "Endpoint for update profile by UserProfile id ")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> uploadImage(@PathVariable("id") Long id, @Valid @RequestBody UploadProfileImageRequest uploadProfileImageRequest){
        return userProfileService.uploadImage(id, uploadProfileImageRequest);
    }
}

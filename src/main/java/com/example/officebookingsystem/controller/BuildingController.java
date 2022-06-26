package com.example.officebookingsystem.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.officebookingsystem.domain.dto.request.FacilityCategoryCreateRequest;
import com.example.officebookingsystem.domain.dto.request.FacilityCreateRequest;
import com.example.officebookingsystem.domain.dto.response.FacilityCategoryResponse;
import com.example.officebookingsystem.domain.dto.response.FacilityResponse;
import com.example.officebookingsystem.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.officebookingsystem.domain.dto.request.BuildingRequest;
import com.example.officebookingsystem.domain.dto.response.BuildingResponse;
import com.example.officebookingsystem.domain.entity.Building;
import com.example.officebookingsystem.service.BuildingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/page")
@Api(tags = "Admin Managing Building API", description = "Buildings")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    @Autowired
    private FacilityService facilityService;

    // Create buildings
    @ApiOperation(value = "Create Building", notes = "Endpoint for admin creating building")
    @PostMapping("/admin/building/create")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createBuilding(@Valid @RequestBody BuildingRequest buildingRequest) {
        return buildingService.create(buildingRequest);
    }

    // Find all buildings
    @ApiOperation(value = "Get All Buildings", notes = "Endpoint for admin get all buildings")
    @GetMapping("/admin/building")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BuildingResponse>> findAllBuilding() {
        return buildingService.adminFindAll();
    }

    // Find building by id
    @ApiOperation(value = "Get Building By Id", notes = "Endpoint for admin get building by id")
    @GetMapping("/admin/building/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Building> adminFindById(@PathVariable("id") Long id) {
        return buildingService.findById(id);
    }

    // Find building by complexId
    @ApiOperation(value = "Get Building By Complex Id", notes = "Endpoint for admin get building by complex id")
    @GetMapping("/admin/building/complex/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Building>> adminFindByComplexId(@PathVariable("id") Long id) {
        return buildingService.adminFindByComplexId(id);
    }

    // update building
    @ApiOperation(value = "Update Building", notes = "Endpoint for admin update building")
    @PutMapping("/admin/building/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Building> updateBuilding(@PathVariable("id") Long id,
            @Valid @RequestBody BuildingRequest buildingRequest) {
        return buildingService.update(id, buildingRequest);
    }

    // Delete building
    @ApiOperation(value = "Delete Building", notes = "Endpoint for delete building")
    @DeleteMapping("/admin/building/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteBuilding(@PathVariable("id") Long id) {
        return buildingService.deleteOne(id);
    }

    @PostMapping("admin/building/facility/category")
    @ApiOperation(value = "Add Nearby Facility Category", notes = "Endpoint for Adding Facility Category for The Building")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacilityCategoryResponse> addFacilityCategory(@Valid@RequestBody FacilityCategoryCreateRequest facilityCategoryCreateRequest){
        return facilityService.addCategory(facilityCategoryCreateRequest);
    }

    @PostMapping("admin/building/facility/create")
    @ApiOperation(value = "Add Nearby Facility by Category", notes = "Endpoint for Adding Faciity for The Building")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FacilityResponse> addFacility(@Valid@RequestBody FacilityCreateRequest facilityCreateRequest){
        return facilityService.addFacility(facilityCreateRequest);
    }
}

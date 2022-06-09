package com.example.officebookingsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.officebookingsystem.domain.dto.request.BuildingRequest;
import com.example.officebookingsystem.domain.dto.response.ResponseData;
import com.example.officebookingsystem.domain.entity.Building;
import com.example.officebookingsystem.service.BuildingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/buildings")
@Api(tags = "Buildings Controller", description = "Buildings")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    // Create buildings
    @ApiOperation(value = "Create Building", notes = "Endpoint for creating building")
    @PostMapping("/")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseData<Building>> createBuilding(@Valid @RequestBody BuildingRequest buildingRequest) {
        return buildingService.create(buildingRequest);
    }

    // Find all buildings
    @ApiOperation(value = "Get All Buildings", notes = "Endpoint for get all buildings")
    @GetMapping("/")
    // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ResponseData<List<Building>>> findAllBuilding() {
        return buildingService.findAll();
    }

    // Find building by id
    @ApiOperation(value = "Get Building By Id", notes = "Endpoint for get building by id")
    @GetMapping("/{id}")
    // @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ResponseData<Building>> findBuildingById(@PathVariable("id") Long id) {
        return buildingService.findById(id);
    }

    //update building
    @ApiOperation(value = "Update Building", notes = "Endpoint for update building")
    @PostMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseData<Building>> updateBuilding(@PathVariable("id") Long id, @Valid @RequestBody BuildingRequest buildingRequest) {
        return buildingService.update(id, buildingRequest);
    }

    // Delete building
    @ApiOperation(value = "Delete Building", notes = "Endpoint for delete building")
    @DeleteMapping("/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteBuilding(@PathVariable("id") Long id) {
        return buildingService.deleteOne(id);
    }

}

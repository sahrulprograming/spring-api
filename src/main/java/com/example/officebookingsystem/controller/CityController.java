package com.example.officebookingsystem.controller;

import com.example.officebookingsystem.domain.dto.request.CityRequest;
import com.example.officebookingsystem.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/page/admin")
@Api(tags = "Admin Managing City API", description = "Cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/city/create")
    @ApiOperation(value = "Create City by Province Id", notes = "Endpoint for adding City to the database by Province Id")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCityByProvince(@RequestBody CityRequest cityRequest) {
        return cityService.createCityByProvinceId(cityRequest);
    }

    @GetMapping("/city/list/{id}")
    @ApiOperation(value = "List Cities by Province", notes = "Endpoint for listing all cities by province")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> listCityByProvinceId(@PathVariable("id") Long id) {
        return cityService.listCityByProvinceId(id);
    }

}
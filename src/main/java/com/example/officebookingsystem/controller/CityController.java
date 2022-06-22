package com.example.officebookingsystem.controller;

import com.example.officebookingsystem.domain.dto.request.CityRequest;
import com.example.officebookingsystem.service.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/page/admin")
@Api(tags = "Admin Managing Building City API", description = "Cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/city/create")
    @ApiOperation(value = "Create City", notes = "Endpoint for adding City to the database")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createCity(@RequestBody CityRequest cityRequest){
        return cityService.createCity(cityRequest);
    }

    @GetMapping("/city/findAll")
    @ApiOperation(value = "List Cities", notes = "Endpoint for listing all cities")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> listCity(){
        return cityService.listCity();
    }


}

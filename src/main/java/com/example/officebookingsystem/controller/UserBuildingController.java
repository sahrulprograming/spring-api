package com.example.officebookingsystem.controller;

import com.example.officebookingsystem.domain.dto.response.ResponseData;
import com.example.officebookingsystem.domain.dto.response.UserResponseBuilding;
import com.example.officebookingsystem.service.UserBuildingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/page/user/building")
public class UserBuildingController {
    @Autowired
    private UserBuildingService userBuildingService;

    @GetMapping("/list/{id}")
    @ApiOperation(value = "List Building", notes = "Endpoint for Listing all Building by City Id")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<UserResponseBuilding>> listBuilding(@PathVariable("id") Long id){
        return userBuildingService.getAllBuildingsById(id);
    }




}

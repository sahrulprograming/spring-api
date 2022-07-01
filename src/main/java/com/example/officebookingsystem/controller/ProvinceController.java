package com.example.officebookingsystem.controller;

import com.example.officebookingsystem.domain.dto.request.ProvinceCreateRequest;
import com.example.officebookingsystem.domain.entity.Province;
import com.example.officebookingsystem.service.ProvinceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/page/admin")
@CrossOrigin(origins = "*", maxAge = 3600L)
@Api(tags = "Admin Managing Provinces API", description = "Provinces")
public class ProvinceController {

    @Autowired
    private ProvinceService provinceService;

    @PostMapping("/province/create")
    @ApiOperation(value = "Create Provinces", notes = "Endpoint for admin creating building")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addProvince(@Valid@RequestBody ProvinceCreateRequest provinceCreateRequest){
        return provinceService.addProvince(provinceCreateRequest);
    }

    @GetMapping("/province/findAll")
    @ApiOperation(value = "Get All Provinces", notes = "Endpoint for admin ger All Provinces")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Province>> listAllProvince(){
        return provinceService.listAllProvince();
    }
}

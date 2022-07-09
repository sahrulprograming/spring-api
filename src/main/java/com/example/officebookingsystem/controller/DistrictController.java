package com.example.officebookingsystem.controller;
import com.example.officebookingsystem.domain.dto.request.DistrictCreateRequest;
import com.example.officebookingsystem.service.DistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/page/admin")
@CrossOrigin(origins = "*", maxAge = 3600L)
@Api(tags = "Admin Managing Districts API", description = "Districts")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @PostMapping("/district/create")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create District", notes = "Endpoint for Creating District by City")
    public ResponseEntity<?> createDistrict(@RequestBody DistrictCreateRequest districtCreateRequest){
        return districtService.createDistrict(districtCreateRequest);
    }

    @GetMapping("/district/list/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "List District", notes = "Endpoint for listing all Districts by City")
    public ResponseEntity<?> listDistrictByCityId(@PathVariable("id") Long id){
        return districtService.listDistrictByCity(id);
    }

}

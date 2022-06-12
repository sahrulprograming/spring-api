package com.example.officebookingsystem.controller;
import com.example.officebookingsystem.domain.dto.request.DistrictCreateRequest;
import com.example.officebookingsystem.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/page")
public class DistrictController {

    @Autowired
    private DistrictService districtService;

    @PostMapping("/admin/district/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createDistrict(@RequestBody DistrictCreateRequest districtCreateRequest){
       return districtService.createDistrict(districtCreateRequest);
    }

    @GetMapping("admin/district/list/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> listDistrictByCityId(@PathVariable("id") Long id){
        return districtService.listDistrictByCity(id);
    }

}

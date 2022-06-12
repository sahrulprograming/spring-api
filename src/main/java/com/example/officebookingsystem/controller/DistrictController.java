package com.example.officebookingsystem.controller;

<<<<<<< HEAD
import com.example.officebookingsystem.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
=======
import com.example.officebookingsystem.domain.dto.request.DistrictCreateRequest;
import com.example.officebookingsystem.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/page")
>>>>>>> 3d11ab2fbbd9c757f0aa11ec800da6146a32a92d
public class DistrictController {

    @Autowired
    private DistrictService districtService;

<<<<<<< HEAD

=======
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
>>>>>>> 3d11ab2fbbd9c757f0aa11ec800da6146a32a92d
}

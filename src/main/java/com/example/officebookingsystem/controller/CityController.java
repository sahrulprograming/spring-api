package com.example.officebookingsystem.controller;

import com.example.officebookingsystem.domain.dto.request.CityRequest;
import com.example.officebookingsystem.domain.dto.response.CityResponse;
import com.example.officebookingsystem.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/page/admin")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/city/create")
    public ResponseEntity<?> createCity(@RequestBody CityRequest cityRequest){
        return cityService.createCity(cityRequest);
    }

    @GetMapping("/city/findAll")
    public ResponseEntity<?> listCity(){
        return cityService.listCity();
    }


}

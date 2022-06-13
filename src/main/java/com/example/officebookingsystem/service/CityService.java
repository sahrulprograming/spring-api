package com.example.officebookingsystem.service;

import com.example.officebookingsystem.domain.dto.request.CityRequest;
import com.example.officebookingsystem.domain.dto.response.CityResponse;
import com.example.officebookingsystem.domain.dto.response.MessageResponse;
import com.example.officebookingsystem.domain.entity.City;
import com.example.officebookingsystem.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    public ResponseEntity<?> createCity(CityRequest cityRequest){



        CityResponse cityResponse = new CityResponse();
        cityResponse.setName(cityRequest.getName());


        if(cityRepository.existsByName(cityRequest.getName())){
            String errorResponse = String.format("District with the name %s is already taken", cityRequest.getName());
            return ResponseEntity.badRequest().body(new MessageResponse(errorResponse));
        }

        City city = new City();
        city.setName(cityRequest.getName());
        cityRepository.save(city);
        return ResponseEntity.ok().body(new CityResponse(cityResponse.getName(), city.getId()));
    }

    public ResponseEntity<?> listCity(){
        return ResponseEntity.ok(cityRepository.getAllCities());
    }
}

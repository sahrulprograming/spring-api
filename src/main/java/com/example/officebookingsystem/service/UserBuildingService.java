package com.example.officebookingsystem.service;
import com.example.officebookingsystem.domain.dto.response.UserResponseBuilding;
import com.example.officebookingsystem.domain.entity.City;
import com.example.officebookingsystem.domain.repository.BuildingRepository;
import com.example.officebookingsystem.domain.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserBuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private CityRepository cityRepository ;


    public ResponseEntity <List<UserResponseBuilding>> getAllBuildingsById( Long id){
        Optional<City> city= cityRepository.findById(id);

        if(!city.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(buildingRepository.getAllBuildingsById((id)));
    }

}

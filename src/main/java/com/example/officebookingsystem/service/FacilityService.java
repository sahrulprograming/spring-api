package com.example.officebookingsystem.service;

import com.example.officebookingsystem.domain.dto.request.FacilityCreateRequest;

import com.example.officebookingsystem.domain.entity.Building;
import com.example.officebookingsystem.domain.entity.Facility;
import com.example.officebookingsystem.domain.repository.BuildingRepository;
import com.example.officebookingsystem.domain.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacilityService {
    @Autowired
    private FacilityRepository nearByFacilityRepository;

    @Autowired
    private BuildingRepository buildingRepository;


    public ResponseEntity<FacilityCreateRequest> addFacility(FacilityCreateRequest facilityCreateRequest) {
        Optional<Building> building = buildingRepository.findById(facilityCreateRequest.getBuilding_id());

        if (!building.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Facility nearByFacility = new Facility();
        nearByFacility.setName(facilityCreateRequest.getName());
        nearByFacility.setDescription(facilityCreateRequest.getDescription());
        nearByFacility.setBuilding(building.get());
        nearByFacilityRepository.save(nearByFacility);

        return ResponseEntity.status(HttpStatus.CREATED).body(facilityCreateRequest);
    }
}

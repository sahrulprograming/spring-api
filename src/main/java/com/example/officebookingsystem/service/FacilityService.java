package com.example.officebookingsystem.service;

import com.example.officebookingsystem.domain.dto.request.FacilityCreateRequest;

import com.example.officebookingsystem.domain.dto.response.FacilityResponse;
import com.example.officebookingsystem.domain.entity.Building;
import com.example.officebookingsystem.domain.entity.NearByFacility;
import com.example.officebookingsystem.domain.repository.BuildingRepository;
import com.example.officebookingsystem.domain.repository.NearByFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacilityService {
    @Autowired
    private NearByFacilityRepository nearByFacilityRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private

    public ResponseEntity<List<FacilityResponse>> addFacility(FacilityCreateRequest facilityCreateRequest){
        Optional<Building> building = buildingRepository.findById(facilityCreateRequest.getBuilding_id());

        if(!building.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body()
        }
        NearByFacility nearByFacility = new NearByFacility();

        nearByFacility.setName(facilityCreateRequest.getName());
        if ()
        nearByFacility.setBuilding(buildingRepository.findById(facilityCreateRequest.getBuilding_id()).get());

}

package com.example.officebookingsystem.service;

import com.example.officebookingsystem.domain.dto.request.FacilityCategoryCreateRequest;

import com.example.officebookingsystem.domain.dto.request.FacilityCreateRequest;
import com.example.officebookingsystem.domain.dto.response.FacilityCategoryResponse;
import com.example.officebookingsystem.domain.dto.response.FacilityResponse;
import com.example.officebookingsystem.domain.entity.Building;
import com.example.officebookingsystem.domain.entity.Facility;
import com.example.officebookingsystem.domain.entity.Facility_Category;
import com.example.officebookingsystem.domain.repository.BuildingRepository;
import com.example.officebookingsystem.domain.repository.FacilityCategoryRepository;
import com.example.officebookingsystem.domain.repository.FacilityRepository;
import net.bytebuddy.asm.Advice;
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
    private FacilityCategoryRepository facilityCategoryRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FacilityRepository facilityRepository;


    public ResponseEntity<FacilityCategoryResponse> addCategory(FacilityCategoryCreateRequest facilityCreateRequest) {
        Optional<Building> building = buildingRepository.findById(facilityCreateRequest.getBuilding_id());

        if (!building.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Facility_Category nearByFacility = new Facility_Category();
        nearByFacility.setName(facilityCreateRequest.getName());
        nearByFacility.setBuilding(building.get());
        facilityCategoryRepository.save(nearByFacility);

        FacilityCategoryResponse facilityCategoryResponse = new FacilityCategoryResponse();
        facilityCategoryResponse.setName(nearByFacility.getName());
        facilityCategoryResponse.setId(nearByFacility.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(facilityCategoryResponse);
    }

    public ResponseEntity<FacilityResponse> addFacility(FacilityCreateRequest facilityCreateRequest){
        Optional<Building> building = buildingRepository.findById(facilityCreateRequest.getBuilding_id());
        Optional<Facility_Category> category = facilityCategoryRepository.findById(facilityCreateRequest.getBuilding_category_id());

        if (!building.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        if (!category.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Facility facility = new Facility();
        facility.setName(facilityCreateRequest.getName());
        facility.setFacility_category(category.get());
        facility.setBuilding(building.get());
        facility.setDistance(facilityCreateRequest.getDistance());
        facility.setDuration(facilityCreateRequest.getDuration());
        facilityRepository.save(facility);

        FacilityResponse facilityResponse = new FacilityResponse();
        facilityResponse.setName(facility.getName());
        facilityResponse.setDistance(facility.getDistance());
        facilityResponse.setDuration(facility.getDuration());
        facilityResponse.setBuilding_name(facility.getBuilding().getName());

        return ResponseEntity.status(HttpStatus.CREATED).body(facilityResponse);
    }

    public ResponseEntity<List<FacilityCategoryResponse>> listFacilityCategories(){
        List<Facility_Category> facilityCategories = facilityCategoryRepository.findAll();
        List<FacilityCategoryResponse> facilityCategoryResponse = new ArrayList<>();
        for(Facility_Category facility_category : facilityCategories){
            FacilityCategoryResponse facilityCategory = new FacilityCategoryResponse();
            facilityCategory.setName(facility_category.getName());
            facilityCategory.setId(facility_category.getId());
        }
        if(facilityCategories.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.FOUND).body(facilityCategoryResponse);
    }
}

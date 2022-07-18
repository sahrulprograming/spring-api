package com.example.officebookingsystem.service;
import com.example.officebookingsystem.domain.dto.request.FacilityCategoryCreateRequest;
import com.example.officebookingsystem.domain.entity.Facility_Category;
import com.example.officebookingsystem.domain.repository.BuildingRepository;
import com.example.officebookingsystem.domain.repository.FacilityCategoryRepository;
import com.example.officebookingsystem.domain.repository.FacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class FacilityService {
    @Autowired
    private FacilityCategoryRepository facilityCategoryRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private FacilityRepository facilityRepository;


    public ResponseEntity<Facility_Category> addCategory(FacilityCategoryCreateRequest facilityCreateRequest) {
        Facility_Category nearByFacility = new Facility_Category();
        nearByFacility.setName(facilityCreateRequest.getName());
        facilityCategoryRepository.save(nearByFacility);
        return ResponseEntity.status(HttpStatus.CREATED).body(nearByFacility);
    }

    public ResponseEntity<List<Facility_Category>> listFacilityCategories(){
        List<Facility_Category> facilityCategories = facilityCategoryRepository.findAll();
        if(facilityCategories.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(facilityCategories);
    }
}

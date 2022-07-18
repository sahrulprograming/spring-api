package com.example.officebookingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.officebookingsystem.domain.dto.request.BuildingUpdateRequest;
import com.example.officebookingsystem.domain.dto.request.FacilityCreateRequest;
import com.example.officebookingsystem.domain.dto.request.FacilityRequestUpdate;
import com.example.officebookingsystem.domain.dto.response.FacilityResponse;
import com.example.officebookingsystem.domain.entity.Facility;
import com.example.officebookingsystem.domain.entity.Facility_Category;
import com.example.officebookingsystem.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.officebookingsystem.domain.dto.request.BuildingRequest;
import com.example.officebookingsystem.domain.dto.response.BuildingResponse;
import com.example.officebookingsystem.domain.dto.response.MessageResponse;
import com.example.officebookingsystem.domain.entity.Building;

@Service
@Transactional
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ComplexRepository complexRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private FacilityCategoryRepository facilityCategoryRepository;

    @Autowired
    private FacilityRepository facilityRepository;

    // Service Create Building
    public ResponseEntity<?> create(BuildingRequest buildingRequest) {
        if (buildingRepository.existsByName(buildingRequest.getName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error:Username is Already taken"));
        }
        Building building = new Building();
        building.setName(buildingRequest.getName());
        building.setAddress(buildingRequest.getAddress());
        if (buildingRequest.getIdComplex() != null) {
            building.setComplex(complexRepository.findById(buildingRequest.getIdComplex()).get());
        }
        building.setDescription(buildingRequest.getDescription());
        building.setBuilding_image(buildingRequest.getBuildingImage());
        building.setImage_type(buildingRequest.getImageType());
        buildingRepository.save(building);


        Facility facility = new Facility();
        List<FacilityCreateRequest> facilityRequest = buildingRequest.getFacilities();

        for(FacilityCreateRequest f : facilityRequest){
            Optional<Building> buildingOptional = buildingRepository.findById(building.getId());
            Optional<Facility_Category> facility_category = facilityCategoryRepository.findById(f.getFacility_category_id());
            if(!facility_category.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Facility Category With " + facility_category.get() + "Doesn't Exist"));
            }
            facility.setBuilding(buildingOptional.get());
            facility.setId(building.getId());
            facility.setName(f.getFacility_name());
            facility.setFacility_category(facility_category.get());
            facility.setDistance(f.getDistance());
            facility.setDuration(f.getDuration());
            facilityRepository.save(facility);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(buildingRequest);
    }
    // Service Find All Buildings
    public ResponseEntity<List<BuildingResponse>> adminFindAll() {
        List<Building> building = buildingRepository.findAll();
        List<BuildingResponse> buildingResponse = new ArrayList<>();
        for (Building b : building) {
            BuildingResponse br = new BuildingResponse();
            br.setBuildingName(b.getName());
            if (b.getComplex() != null) {
                // mengambil nama complex berdasarkan index building
                br.setComplexName(b.getComplex().getComplexName());
                // mengambil alamat pada complex berdasarkan index building
                br.setAddress(b.getAddress());

                br.setId(b.getId());
            }
            // mengambil jumlah room pada building
            Integer numOfRooms = roomRepository.countByBuilding(b);
            if (numOfRooms != null) {
                br.setNumOfRooms(numOfRooms);
            }
            br.setDescription(b.getDescription());
            List <Facility> facilities = facilityRepository.getFacilitiesByBuildingId(b.getId());
            List <FacilityResponse> facilityResponses = new ArrayList<>();
            for(Facility facility: facilities){
                FacilityResponse facilityResponse = new FacilityResponse();
                facilityResponse.setName(facility.getName());
                facilityResponse.setDuration(facility.getDuration());
                facilityResponse.setDistance(facility.getDistance());
                facilityResponse.setCategoryId(facility.getFacility_category().getId());
                facilityResponses.add(facilityResponse);
                facilityResponse.setFacilityId(facility.getId());
            }
            br.setFacilityResponseList(facilityResponses);
            br.setBase64Image(b.getBuilding_image());
            br.setImageType(b.getImage_type());
            buildingResponse.add(br);
        }
        if (building.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(buildingResponse);
    }



    // Service Get Building By Id
    public ResponseEntity<Building> findById(Long id) {
        Optional<Building> building = buildingRepository.findById(id);
        if (building.isPresent() == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok().body(building.get());
    }

    // Service Update Building
    public ResponseEntity<?> update(Long id, BuildingUpdateRequest buildingRequest) {
        Optional<Building> building = buildingRepository.findById(id);
        if (building.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("BUilding Doesn't Exists"));
        }

        if (buildingRequest.getIdComplex() != null) {
            building.get().setDescription(buildingRequest.getDescription());
        }

        if(complexRepository.findById(buildingRequest.getIdComplex()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Complex Doesnt Exists"));
        }
        building.get().setComplex(complexRepository.findById(buildingRequest.getIdComplex()).get());
        building.get().setName(buildingRequest.getName());
        building.get().setAddress(buildingRequest.getAddress());
        building.get().setBuilding_image(buildingRequest.getBuildingImage());
        building.get().setImage_type(buildingRequest.getImageType());
        building.get().setDescription(buildingRequest.getDescription());

        List<FacilityRequestUpdate> facilityUpdateRequest = buildingRequest.getFacilities();
        for(FacilityRequestUpdate facilityUpdate: facilityUpdateRequest){
            Optional<Facility> facility = facilityRepository.findById(facilityUpdate.getFacilityId());
            facility.get().setFacility_category(facilityCategoryRepository.findById(facilityUpdate.getFacility_category_id()).get());
            facility.get().setDuration(facilityUpdate.getDuration());
            facility.get().setName(facilityUpdate.getFacility_name());
            facility.get().setDistance(facilityUpdate.getDistance());
        }

        return ResponseEntity.status(HttpStatus.OK).body(buildingRequest);
    }

    // Service deleteOne Building
    public ResponseEntity<?> deleteOne(Long id) {
        if (buildingRepository.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Error:Building with id " + id + " not found"));
        }
        buildingRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new MessageResponse("Success:Building with id " + id + " deleted"));
    }

    // Service Get Building By Complex Id
    public ResponseEntity<List<Building>> adminFindByComplexId(Long id) {
        List<Building> building = buildingRepository.findByComplexId(id);
        if (building.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(building);
    }
}

package com.example.officebookingsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.officebookingsystem.domain.dto.request.BuildingRequest;
import com.example.officebookingsystem.domain.dto.response.BuildingResponse;
import com.example.officebookingsystem.domain.dto.response.MessageResponse;
import com.example.officebookingsystem.domain.entity.Building;
import com.example.officebookingsystem.domain.repository.BuildingRepository;
import com.example.officebookingsystem.domain.repository.ComplexRepository;
import com.example.officebookingsystem.domain.repository.RoomRepository;

@Service
@Transactional
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ComplexRepository complexRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Service Create Building
    public ResponseEntity<?> create(BuildingRequest buildingRequest) {
        if (buildingRepository.existsByName(buildingRequest.getName())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error:Username is Already taken"));
        }
        Building building = new Building();
        building.setName(buildingRequest.getName());
        building.setAddress(buildingRequest.getAddress());
        building.setDescription(buildingRequest.getDescription());
        if (buildingRequest.getIdComplex() != null) {
            building.setComplex(complexRepository.findById(buildingRequest.getIdComplex()).get());
        }
        return ResponseEntity.ok().body(buildingRepository.save(building));
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
                br.setComplexAdress(b.getComplex().getAddress());

                br.setId(b.getId());
            }
            // mengambil jumlah room pada building
            Integer numOfRooms = roomRepository.countByBuilding(b);
            if (numOfRooms != null) {
                br.setNumOfRooms(numOfRooms);
            }
            br.setDescription(b.getDescription());
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
    public ResponseEntity<Building> update(Long id, BuildingRequest buildingRequest) {
        Optional<Building> building = buildingRepository.findById(id);
        if (building == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        building.get().setName(buildingRequest.getName());
        building.get().setAddress(buildingRequest.getAddress());
        building.get().setDescription(buildingRequest.getDescription());
        if (buildingRequest.getIdComplex() != null) {
            building.get().setComplex(complexRepository.findById(buildingRequest.getIdComplex()).get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(building.get());
    }

    // Service deleteOne Building
    public ResponseEntity<?> deleteOne(Long id) {
        if (buildingRepository.existsById(id) == false) {
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

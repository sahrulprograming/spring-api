package com.example.officebookingsystem.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.example.officebookingsystem.domain.entity.Complex;
import com.example.officebookingsystem.domain.entity.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.officebookingsystem.domain.dto.request.BuildingRequest;
import com.example.officebookingsystem.domain.dto.response.ResponseData;
import com.example.officebookingsystem.domain.entity.Building;
import com.example.officebookingsystem.domain.helpers.ResponseHelper;
import com.example.officebookingsystem.domain.repository.BuildingRepository;
import com.example.officebookingsystem.domain.repository.ComplexRepository;

@Service
@Transactional
public class BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private ComplexRepository complexRepository;

    @Autowired
    private ResponseHelper responseHelper;


    public ResponseEntity<ResponseData<BuildingRequest>> create(BuildingRequest buildingRequest) {
        Optional <Complex> optionalComplex = complexRepository.findById(buildingRequest.getComplex_id());
        if (buildingRepository.existsByName(buildingRequest.getBuilding_name())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    responseHelper.response(HttpStatus.BAD_REQUEST.value(), "ERROR :Building is Already taken", null));
        }

        if (!optionalComplex.isPresent()){
            String falseResponse = String.format("ERROR : Complex with id %s doesn't exists", (buildingRequest.getComplex_id()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    responseHelper.response(HttpStatus.BAD_REQUEST.value(),falseResponse,null) );

        }
        Building building = new Building();
        building.setName(buildingRequest.getBuilding_name());
        building.setAddress(buildingRequest.getAddress());
        building.setDescription(buildingRequest.getDescription());
        if (buildingRequest.getComplex_id() != null) {
            building.setComplex(optionalComplex.get());
        }
        buildingRepository.save(building);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseHelper.response(HttpStatus.CREATED.value(),
                "Building Created Successfully", buildingRequest));
    }

    public ResponseEntity<ResponseData<List<Building>>> findAll() {
        List<Building> building = buildingRepository.findAll();
        if (building.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseHelper.response(HttpStatus.NOT_FOUND.value(), "Error:Building Not Found", null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseHelper.response(HttpStatus.OK.value(), "Success", building));
    }

    public ResponseEntity<ResponseData<Building>> findById(Long id) {
        Building building = buildingRepository.findById(id).get();
        if (building == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseHelper.response(HttpStatus.NOT_FOUND.value(), "Error:Building Not Found", null));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseHelper.response(HttpStatus.OK.value(), "Success", building));
    }

    // Update Building Service
    public ResponseEntity<ResponseData<Building>> update(Long id, BuildingRequest buildingRequest) {
        Building building = buildingRepository.findById(id).get();
        if (building == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseHelper.response(HttpStatus.NOT_FOUND.value(), "Error:Building Not Found", null));
        }
        building.setName(buildingRequest.getBuilding_name());
        building.setAddress(buildingRequest.getAddress());
        building.setDescription(buildingRequest.getDescription());
        if (buildingRequest.getComplex_id() != null) {
            building.setComplex(complexRepository.findById(buildingRequest.getComplex_id()).get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseHelper.response(HttpStatus.OK.value(),
                "Building Updated Successfully", buildingRepository.save(building)));
    }


    public ResponseEntity<?> deleteOne(Long id) {
        if (buildingRepository.existsById(id) == false) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(responseHelper.response(HttpStatus.NOT_FOUND.value(), "Error:Building Not Found", null));
        }
        buildingRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseHelper.response(HttpStatus.OK.value(),
                "Building Deleted Successfully", null));
    }
}

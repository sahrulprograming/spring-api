package com.example.officebookingsystem.service;
import com.example.officebookingsystem.domain.dto.request.ComplexCreateRequest;
import com.example.officebookingsystem.domain.dto.response.ComplexResponse;
import com.example.officebookingsystem.domain.dto.response.MessageResponse;
import com.example.officebookingsystem.domain.entity.City;
import com.example.officebookingsystem.domain.entity.Complex;
import com.example.officebookingsystem.domain.entity.District;
import com.example.officebookingsystem.domain.repository.BuildingRepository;
import com.example.officebookingsystem.domain.repository.CityRepository;
import com.example.officebookingsystem.domain.repository.ComplexRepository;
import com.example.officebookingsystem.domain.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ComplexService {

    @Autowired
    private ComplexRepository complexRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private BuildingRepository buildingRepository;


    public ResponseEntity<ComplexCreateRequest> createComplex(ComplexCreateRequest complexCreateRequest){
        Optional<City> optionalCity = cityRepository.findById(complexCreateRequest.getCity_id());
        Optional<District> optionalDistrict = districtRepository.findById(complexCreateRequest.getDistrict_id());

        if(complexRepository.existsByComplexName(complexCreateRequest.getComplex_name())){
            // String errorResponse = String.format("Complex with the name %s is already taken", complexCreateRequest.getComplex_name());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Complex complex = new Complex();
        complex.setComplexName(complexCreateRequest.getComplex_name());
        complex.setAddress(complexCreateRequest.getStreet());
        complex.setCity(optionalCity.get());
        complex.setDistrict(optionalDistrict.get());
        complexRepository.save(complex);
        return ResponseEntity.status(HttpStatus.CREATED).body(complexCreateRequest);
    }

    public ResponseEntity<List<ComplexResponse>> listComplex(){
        List<Complex> complexList = complexRepository.findAll();
        List<ComplexResponse> complexResponseList = new ArrayList<>();
        for(Complex c : complexList ){
            ComplexResponse complexResponse = new ComplexResponse();
            complexResponse.setComplex_name(c.getComplexName());
            complexResponse.setAddress(c.getAddress());
            complexResponse.setCity_name(c.getCity().getName());
            complexResponse.setDistrict_name(c.getDistrict().getName());
            complexResponse.setId(c.getId());
            Integer numOfBuildings = buildingRepository.countByComplex(c);
            if(numOfBuildings!= null){
                complexResponse.setNumOfBuilding(numOfBuildings);
            }

            complexResponseList.add(complexResponse);
        }

        if(complexList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).body(complexResponseList);
    }

    public ResponseEntity<?> deleteById(Long id){
        if(complexRepository.existsById(id)== false){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("ERROR : building with " + id + "doesn't exists"));
        }
        complexRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Complex successfully created"));
    }

    public ResponseEntity<Complex> update(Long id, ComplexCreateRequest complexCreateRequest) {
        Optional<Complex> complex = complexRepository.findById(id);
        if (complex == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        complex.get().setComplexName(complexCreateRequest.getComplex_name());
        complex.get().setAddress(complexCreateRequest.getStreet());

        if (districtRepository.getById(id) != null) {
            complex.get().setDistrict(districtRepository.findById(complexCreateRequest.getDistrict_id()).get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(complex.get());
    }
}

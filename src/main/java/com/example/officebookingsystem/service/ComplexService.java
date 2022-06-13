package com.example.officebookingsystem.service;
import com.example.officebookingsystem.domain.dto.request.ComplexCreateRequest;
import com.example.officebookingsystem.domain.dto.response.ComplexResponse;
import com.example.officebookingsystem.domain.entity.City;
import com.example.officebookingsystem.domain.entity.Complex;
import com.example.officebookingsystem.domain.entity.District;
import com.example.officebookingsystem.domain.repository.CityRepository;
import com.example.officebookingsystem.domain.repository.ComplexRepository;
import com.example.officebookingsystem.domain.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
        return ResponseEntity.status(HttpStatus.OK).body(complexRepository.getAllComplex());
    }

}

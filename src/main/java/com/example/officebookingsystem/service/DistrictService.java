package com.example.officebookingsystem.service;

import com.example.officebookingsystem.domain.entity.District;
import com.example.officebookingsystem.domain.dto.request.DistrictCreateRequest;
import com.example.officebookingsystem.domain.dto.response.DistrictResponse;
import com.example.officebookingsystem.domain.dto.response.MessageResponse;
import com.example.officebookingsystem.domain.entity.City;
import com.example.officebookingsystem.domain.repository.CityRepository;
import com.example.officebookingsystem.domain.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private CityRepository cityRepository;

    public ResponseEntity<?> createDistrict(DistrictCreateRequest districtCreateRequest){
        Optional<City> optionalCity = cityRepository.findById(districtCreateRequest.getCity_id());
        DistrictResponse districtResponse = new DistrictResponse();
        districtResponse.setName(districtCreateRequest.getName());
        if (districtRepository.existsByName(districtCreateRequest.getName())){
                String errorResponse = String.format("District with the name %s is already taken", districtResponse.getName());
            return ResponseEntity.badRequest().body(new MessageResponse(errorResponse));
        }
        if (!optionalCity.isPresent()){
            String falseResponse = String.format("City with id %s doesn't exists", (districtCreateRequest.getCity_id()));
            return ResponseEntity.badRequest().body(new MessageResponse(falseResponse));
        }
        District district = new District();
        district.setName(districtCreateRequest.getName());
        district.setCity(optionalCity.get());
        districtRepository.save(district);
        return ResponseEntity.ok().body(districtCreateRequest);
    }

    public ResponseEntity<?> listDistrictByCity(Long id){
        Optional<City> optionalCity = cityRepository.findById(id);
        if (!optionalCity.isPresent()){
            String falseResponse = String.format("City with id %s doesn't exists", (id));
            return ResponseEntity.badRequest().body(new MessageResponse(falseResponse));
        }
        return ResponseEntity.ok().body(districtRepository.getALLDistrictByCityId(id));
    }
}


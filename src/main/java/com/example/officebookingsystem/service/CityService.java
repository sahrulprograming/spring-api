package com.example.officebookingsystem.service;

import com.example.officebookingsystem.domain.dto.request.CityRequest;
import com.example.officebookingsystem.domain.dto.response.CityResponse;
import com.example.officebookingsystem.domain.dto.response.MessageResponse;
import com.example.officebookingsystem.domain.entity.City;
import com.example.officebookingsystem.domain.entity.Province;
import com.example.officebookingsystem.domain.repository.CityRepository;
import com.example.officebookingsystem.domain.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    public ResponseEntity<?> createCityByProvinceId(CityRequest cityRequest){
        Optional<Province> province = provinceRepository.findById(cityRequest.getProvince_id());
        CityResponse cityResponse = new CityResponse();
        if(cityRepository.existsByName(cityRequest.getName())){
            String errorResponse = String.format("City with the name %s is already taken", cityRequest.getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(errorResponse));
        }
        if(!province.isPresent()){
            String falseResponse = String.format("Province with id %s doesn't exist", cityRequest.getProvince_id());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(falseResponse));
        }
        City city = new City();
        city.setName(cityRequest.getName());
        city.setProvince(province.get());
        cityRepository.save(city);
        cityResponse.setId(city.getId());
        cityResponse.setProvince_id(city.getProvince().getId());
        cityResponse.setName(city.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(cityResponse);
    }

    public ResponseEntity<?> listCityByProvinceId(Long id){
        if(!provinceRepository.findById(id).isPresent()){
            String falseResponse = String.format("Province with id %s doesn't exist", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(falseResponse));

        }
        return ResponseEntity.status(HttpStatus.OK).body(cityRepository.getCitiesByProvinceId(id));
    }
}

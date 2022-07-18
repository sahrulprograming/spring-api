package com.example.officebookingsystem.service;

import com.example.officebookingsystem.domain.dto.request.ProvinceCreateRequest;
import com.example.officebookingsystem.domain.dto.response.MessageResponse;
import com.example.officebookingsystem.domain.entity.Province;
import com.example.officebookingsystem.domain.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class ProvinceService {
    @Autowired
    ProvinceRepository provinceRepository;

    Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");


    public ResponseEntity<?> addProvince(ProvinceCreateRequest provinceCreateRequest){
        boolean isInteger = pattern.matcher(provinceCreateRequest.getProvince_name()).matches();
        if(isInteger==true){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Please Input a Right Name"));
        }
        if(provinceCreateRequest.getProvince_name()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error : Field Cannot be Empty");
        }

        Province province = new Province();
        province.setName(provinceCreateRequest.getProvince_name());
        provinceRepository.save(province);
        return ResponseEntity.status(HttpStatus.CREATED).body(province);
    }

    public ResponseEntity<List<Province>> listAllProvince(){
        List<Province> provinces = provinceRepository.findAll();
        if (provinces.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        return ResponseEntity.status(HttpStatus.OK).body(provinces);
    }
}

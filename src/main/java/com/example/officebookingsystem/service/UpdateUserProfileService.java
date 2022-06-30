package com.example.officebookingsystem.service;
import com.example.officebookingsystem.domain.dto.request.UploadProfileImageRequest;
import com.example.officebookingsystem.domain.dto.request.UserUpdateProfileRequest;
import com.example.officebookingsystem.domain.dto.response.UserUpdateProfileImageResponse;
import com.example.officebookingsystem.domain.dto.response.UserUpdateResponse;
import com.example.officebookingsystem.domain.entity.*;
import com.example.officebookingsystem.domain.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserProfileService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private ProfileImageRepository profileImageRepository;


    public ResponseEntity<?> editUserProfile(Long id, UserUpdateProfileRequest request){
        Optional<User> user = userRepository.findById(id);
        Optional<Province> province = provinceRepository.findById(request.getProvince_id());
        Optional<City> city = cityRepository.findById(request.getCity_id());
        Optional<District> district = districtRepository.findById(request.getCity_id());
        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Id doesn't exists");
        }
        if(province.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Province Id doesn't exists");
        }

        if(city.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City with that id doesn't exist");
        }
        UserDetails userDetails = new UserDetails();
        UserUpdateResponse userUpdateResponse = new UserUpdateResponse();
        userDetails.setName(request.getName());
        user.get().setEmail(request.getEmail());
        userDetails.setUser(user.get());
        userDetails.setProvince(province.get());
        userDetails.setCity(city.get());
        userDetailRepository.save(userDetails);
        userUpdateResponse.setName(userDetails.getName());
        userUpdateResponse.setEmail(user.get().getEmail());
        userUpdateResponse.setUsername(user.get().getUsername());
        userUpdateResponse.setCity(province.get().getName());
        userUpdateResponse.setDistrict(district.get().getName());
        userUpdateResponse.setUserDetailID(userDetails.getId());
        return ResponseEntity.status(HttpStatus.OK).body(userUpdateResponse);
    }

    public ResponseEntity<?> uploadImage(Long id, UploadProfileImageRequest uploadProfileImageRequest){
        Optional<UserDetails> userDetails = userDetailRepository.findById(id);
        ProfileImage profileImage = new ProfileImage();
        profileImage.setImage(uploadProfileImageRequest.getImage());
        profileImage.setImageType(uploadProfileImageRequest.getImageType());
        profileImage.setUserDetails(userDetails.get());
        profileImageRepository.save(profileImage);
        UserUpdateProfileImageResponse updateImage = new UserUpdateProfileImageResponse();
        updateImage.setImage(profileImage.getImage());
        updateImage.setImage_type(profileImage.getImageType());
        updateImage.setUser_id(userDetails.get().getUser().getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(updateImage);
    }
}

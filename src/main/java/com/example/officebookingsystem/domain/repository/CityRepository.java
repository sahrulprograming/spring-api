package com.example.officebookingsystem.domain.repository;

import com.example.officebookingsystem.domain.dto.response.CityResponse;
import com.example.officebookingsystem.domain.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository <City, Long>{
        Boolean existsByName (String name);

        @Query(value = "SELECT new com.example.officebookingsystem.domain.dto.response.CityResponse(c.name, c.id) FROM City c")
        List<CityResponse> getAllCities();

        

}

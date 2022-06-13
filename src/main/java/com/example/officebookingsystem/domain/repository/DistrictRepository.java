package com.example.officebookingsystem.domain.repository;




import com.example.officebookingsystem.domain.dto.response.DistrictResponse;

import com.example.officebookingsystem.domain.entity.District;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    Boolean existsByName(String name);
    @Query(value = "SELECT new com.example.officebookingsystem.domain.dto.response.DistrictResponse(d.id, d.name,d.city.id,c.name) FROM District d INNER JOIN City c on c.id = d.city.id WHERE d.city.id=:id")
    List<DistrictResponse> getALLDistrictByCityId(@Param("id")Long id);


}

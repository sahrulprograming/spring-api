package com.example.officebookingsystem.domain.repository;

<<<<<<< HEAD
import com.example.officebookingsystem.domain.dto.response.DistrictResponse;
=======
import com.example.officebookingsystem.domain.dto.response.DistrictCreateResponse;
>>>>>>> 3d11ab2fbbd9c757f0aa11ec800da6146a32a92d
import com.example.officebookingsystem.domain.entity.District;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

<<<<<<< HEAD

   
=======
    Boolean existsByName(String name);

    @Query(value = "SELECT new com.example.officebookingsystem.domain.dto.response.DistrictCreateResponse(d.id, d.name,d.city.id,c.name) FROM District d INNER JOIN City c on c.id = d.city.id WHERE d.city.id=:id")
    List<DistrictCreateResponse> getALLDistrictByCityId(@Param("id")Long id);
>>>>>>> 3d11ab2fbbd9c757f0aa11ec800da6146a32a92d

}

package com.example.officebookingsystem.domain.repository;


import com.example.officebookingsystem.domain.dto.response.ComplexResponse;
import com.example.officebookingsystem.domain.entity.Complex;
import com.example.officebookingsystem.domain.entity.District;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplexRepository extends JpaRepository<Complex, Long> {
    Boolean existsByComplexName (String name);

//    @Query(value =
//            "SELECT new com.example.officebookingsystem.domain.dto.response.ComplexResponse(c.id, c.complexName, c.address, s.name, d.name, count(b))" +
//            "FROM Complex c INNER JOIN City s on s.id = c.city.id " +
//            "INNER JOIN District d on d.id = c.district.id INNER JOIN Building b on b.complex.id = c.id " +
//            "GROUP BY c.id, c.complexName, c.address, s.name, d.name")
//    List<ComplexResponse> getAllComplex();
}

package com.example.officebookingsystem.domain.repository;


import com.example.officebookingsystem.domain.entity.Complex;
import com.example.officebookingsystem.domain.entity.District;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplexRepository extends JpaRepository<Complex, Long> {

    List<Complex> findComplexByDistrict(District district);


    List<Complex> findComplexByComplexName(String name);
}

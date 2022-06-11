package com.example.officebookingsystem.domain.repository;

import com.example.officebookingsystem.domain.dto.response.DistrictResponse;
import com.example.officebookingsystem.domain.entity.District;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {


   

}

package com.example.officebookingsystem.domain.repository;

import com.example.officebookingsystem.domain.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacilityRepository extends JpaRepository<Facility,Long> {

    List <Facility> getFacilitiesByBuildingId(Long id);
}

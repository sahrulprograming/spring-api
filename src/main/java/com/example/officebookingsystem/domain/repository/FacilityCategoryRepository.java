package com.example.officebookingsystem.domain.repository;

import com.example.officebookingsystem.domain.entity.Facility_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacilityCategoryRepository extends JpaRepository<Facility_Category, Long> {

}

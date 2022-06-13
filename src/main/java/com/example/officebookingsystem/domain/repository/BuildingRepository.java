package com.example.officebookingsystem.domain.repository;

import com.example.officebookingsystem.domain.entity.Building;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Long> {

    boolean existsByName(String name);

    List<Building> findByName(String name);

    List<Building> findAllByComplexId(Long complexId);
}

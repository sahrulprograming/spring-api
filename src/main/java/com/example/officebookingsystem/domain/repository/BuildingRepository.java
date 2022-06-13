package com.example.officebookingsystem.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.officebookingsystem.domain.entity.Building;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Long> {

    boolean existsByName(String name);

    List<Building> findByName(String name);

    // Find building by complex id
    @Query("SELECT building FROM Building building WHERE building.complex.id = :id")
    List<Building> findByComplexId(Long id);
}

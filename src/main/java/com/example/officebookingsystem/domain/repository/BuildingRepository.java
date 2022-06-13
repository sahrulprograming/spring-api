package com.example.officebookingsystem.domain.repository;

import com.example.officebookingsystem.domain.dto.response.UserResponseBuilding;
import com.example.officebookingsystem.domain.entity.Building;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Long> {

    boolean existsByName(String name);

    List<Building> findByName(String name);

    // Find building by complex id
    @Query("SELECT building FROM Building building WHERE building.complex.id = :id")
    List<Building> findByComplexId(Long id);
    @Query(
            value = "SELECT new com.example.officebookingsystem.domain.dto.response.UserResponseBuilding(b.id, b.name, s.name, b.address )"+
                    "FROM Building b "+
                    "INNER JOIN Complex c ON c.id=b.complex.id "+
                    "INNER JOIN City s ON s.id=c.city.id WHERE s.id =:id"
    )
    List<UserResponseBuilding> getAllBuildingsById(@Param("id") Long id);
}

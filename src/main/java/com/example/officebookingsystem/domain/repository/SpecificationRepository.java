package com.example.officebookingsystem.domain.repository;

import com.example.officebookingsystem.domain.entity.RoomSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationRepository extends JpaRepository<RoomSpecification, Long> {

}

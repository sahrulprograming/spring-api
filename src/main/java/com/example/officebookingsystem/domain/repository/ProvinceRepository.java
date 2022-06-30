package com.example.officebookingsystem.domain.repository;

import com.example.officebookingsystem.domain.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province,Long> {

    Boolean existsByName(String name);
}

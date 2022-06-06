package com.example.officebookingsystem.domain.repository;


import com.example.officebookingsystem.domain.entity.Complex;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplexRepository extends JpaRepository<Complex, Long> {
}

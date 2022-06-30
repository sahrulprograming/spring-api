package com.example.officebookingsystem.domain.repository;

import com.example.officebookingsystem.domain.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetails, Long> {
}

package com.example.officebookingsystem.domain.repository;

import com.example.officebookingsystem.domain.entity.ProfileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileImageRepository extends JpaRepository <ProfileImage,Long> {
}

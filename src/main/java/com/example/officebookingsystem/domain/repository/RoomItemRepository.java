package com.example.officebookingsystem.domain.repository;

import com.example.officebookingsystem.domain.entity.RoomItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomItemRepository extends JpaRepository<RoomItem, Long> {
}

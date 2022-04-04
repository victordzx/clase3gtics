package com.example.clase3gtics.repository;

import com.example.clase3gtics.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface RegionRepository extends JpaRepository<Region, Integer> {
}
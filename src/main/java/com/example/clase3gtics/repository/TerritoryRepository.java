package com.example.clase3gtics.repository;

import com.example.clase3gtics.entity.Territory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerritoryRepository extends JpaRepository<Territory, String> {
    boolean existsById(String id);
}
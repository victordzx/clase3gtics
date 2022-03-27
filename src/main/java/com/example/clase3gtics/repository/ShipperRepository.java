package com.example.clase3gtics.repository;

import com.example.clase3gtics.entity.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {

    List<Shipper> findByCompanyName(String companyName);

    @Query(nativeQuery = true,value = "select * from shippers where CompanyName = ?1")
    List<Shipper> buscarPorNombre(String companyName);

    @Query(nativeQuery = true,value = "select * from shippers where CompanyName like %?1%")
    List<Shipper> buscarPorNombreParcial(String companyName);

}


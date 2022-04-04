package com.example.clase3gtics.entity;

import javax.persistence.*;

@Entity
@Table(name = "region")
public class Region {
    @Id
    @Column(name = "RegionID", nullable = false)
    private Integer id;

    @Column(name = "RegionDescription", nullable = false, length = 50)
    private String regionDescription;

    public String getRegionDescription() {
        return regionDescription;
    }

    public void setRegionDescription(String regionDescription) {
        this.regionDescription = regionDescription;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
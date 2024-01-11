package com.example.SpringbootBasicApplication.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="countries")
@Data
public class Country {

    @Id
    @Column(name="country_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer countryId;

    @Column(name="country_name")
    public String countryName;


}

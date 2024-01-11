package com.example.SpringbootBasicApplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer countryId;
    public String countryName;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "country_stateId")
    public List<States> states;



    @Override
    public String toString() {
        return "Country{" +
                "countryId=" + countryId +
                ", countryName='" + countryName + '\'' +
                ", states=" + states +
                '}';
    }
}

package com.example.SpringbootBasicApplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data

public class States {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer stateId;
    public String stateName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "states_districtId")
    public List<Districts> districts;


    @Override
    public String toString() {
        return "States{" +
                "stateId=" + stateId +
                ", stateName='" + stateName + '\'' +
                '}';
    }
}

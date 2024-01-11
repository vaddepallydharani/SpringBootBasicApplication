package com.example.SpringbootBasicApplication.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
public class Districts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public  int districtId;

    public String districtName;

    @Override
    public String toString() {
        return "Districts{" +
                "districtId=" + districtId +
                ", districtName='" + districtName + '\'' +
                '}';
    }
}

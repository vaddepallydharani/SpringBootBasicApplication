package com.example.SpringbootBasicApplication.model;

import lombok.Data;

@Data
public class AddressAndDOB {

    private Integer empId;

    private String dateOfBirth;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;


}
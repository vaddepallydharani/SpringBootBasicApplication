package com.example.SpringbootBasicApplication.dto;

import com.example.SpringbootBasicApplication.model.AddressAndDOB;
import com.example.SpringbootBasicApplication.entity.Employee;
import com.example.SpringbootBasicApplication.model.Country;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDetailsResponse {

    private Employee employee;
    private AddressAndDOB addressAndDOB;
    private List<Country> countries;
    private String isAddressValidated;

}

package com.example.SpringbootBasicApplication.client;

import com.example.SpringbootBasicApplication.model.AddressAndDOB;

public interface EmployeeClient {
    AddressAndDOB getEmployeeDetails(Integer empId);
}

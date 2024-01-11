package com.example.SpringbootBasicApplication.service;


import com.example.SpringbootBasicApplication.dto.EmployeeDetailsResponse;
import com.example.SpringbootBasicApplication.model.AddressAndDOB;
import com.example.SpringbootBasicApplication.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {


    public Employee saveEmployee( Employee employee);

    public EmployeeDetailsResponse saveEmployeeFullDetails(AddressAndDOB addressAndDOB, Integer empid);

    public Optional<EmployeeDetailsResponse> getEmployee(Integer empid) throws Exception;

    public List<Employee> getAllEmployees();

    public Employee editEmployee( Employee employee);

    public Employee get1Emp( Integer empid);

    public List<Employee> getByEmpName( String empName);

    public List<Employee> getByEmpSalary( Integer empSalary);

    public  String deleteEmployee( Integer empid);



}

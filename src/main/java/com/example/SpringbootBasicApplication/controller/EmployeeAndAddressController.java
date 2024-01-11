package com.example.SpringbootBasicApplication.controller;

import com.example.SpringbootBasicApplication.dto.EmployeeDetailsResponse;
import com.example.SpringbootBasicApplication.entity.Employee;
import com.example.SpringbootBasicApplication.repository.EmployeeRepository;
import com.example.SpringbootBasicApplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employeeFullDetails")
public class EmployeeAndAddressController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping
    public ResponseEntity<String>  saveEmployeeFullDetails(@RequestBody EmployeeDetailsResponse employeeDetailsResponse) {

        Employee saveEmployee = employeeRepository.save(employeeDetailsResponse.getEmployee());

        if (saveEmployee != null && saveEmployee.getEmpId() > 0) {
            employeeService.saveEmployeeFullDetails(employeeDetailsResponse.getAddressAndDOB(), saveEmployee.getEmpId());
        }
        return new ResponseEntity<String>("Employee " +saveEmployee.getEmpName()+" saved successfully with ID: "+saveEmployee.getEmpId(), HttpStatus.CREATED);
    }

    @GetMapping
    public Optional<EmployeeDetailsResponse> getEmployee(@RequestParam Integer empId) throws Exception {

        Optional<EmployeeDetailsResponse> response= employeeService.getEmployee(empId);

        return response;
    }


}

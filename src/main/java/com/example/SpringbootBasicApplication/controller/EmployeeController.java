package com.example.SpringbootBasicApplication.controller;

import com.example.SpringbootBasicApplication.entity.Employee;
import com.example.SpringbootBasicApplication.repository.EmployeeRepository;
import com.example.SpringbootBasicApplication.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return  employeeService.saveEmployee(employee);
    }



    @GetMapping("/fetch-all")
    public List<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }

    @PutMapping
    public Employee editEmployee(@RequestBody Employee employee){
        return employeeService.editEmployee(employee);
    }

   @GetMapping
    public Employee get1Emp(@RequestParam Integer empid){
        return employeeService.get1Emp(empid) ;
    }

    @GetMapping("/fetch-by-name")
    public List<Employee> getByEmpName(@RequestParam String empName) {


        return employeeService.getByEmpName(empName);
    }

    @GetMapping("/fetch-by-salary")
    public List<Employee> getByEmpSalary(@RequestParam Integer empSalary) {


        return employeeService.getByEmpSalary(empSalary);
    }





    @DeleteMapping
    public  String deleteEmployee(@RequestParam Integer empid){

       employeeService.deleteEmployee(empid);
        return "Deleted successfully";
    }


/*
    @GetMapping(value = "/redirect")
    public ResponseEntity<Void> redirect(@RequestParam String resourceType, @RequestParam String sorId){
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectService.redirectServiceUrl(resourceType, sorId))).build();
    }

    @PostMapping(value = "/redirect")
    public ResponseEntity<String> redirect1(@RequestParam String resourceType, @RequestParam String sorId){

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectService.redirectServiceUrl(resourceType, sorId))).build();
    }
*/
}

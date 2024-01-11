package com.example.SpringbootBasicApplication.repository;

import com.example.SpringbootBasicApplication.entity.Employee;
import com.example.SpringbootBasicApplication.model.AddressAndDOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

    List<Employee> findByEmpName(String empName);
    List<Employee> findByEmpSalary(Integer empSalary);
    Optional<Employee> findByEmpId(Integer empId);


}

package com.example.SpringbootBasicApplication.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="emp_id")
    private int empId;
    @Column(name="emp_name")
    private String empName;
    @Column(name="emp_salary")
    private int empSalary;
}

package com.example.SpringbootBasicApplication.repository;

import com.example.SpringbootBasicApplication.entity.Employee;
import com.example.SpringbootBasicApplication.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findBySnameAndAvg(String sname, Integer avg);
    List<Student> findBySname(String sname);


}

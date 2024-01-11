package com.example.SpringbootBasicApplication.service;

import com.example.SpringbootBasicApplication.entity.Country;
import com.example.SpringbootBasicApplication.entity.Student;
import com.example.SpringbootBasicApplication.model.StudentResponse;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface StudentService {


    public List<Student> getAllStudents();
    public Student saveStudents(Student student);

    public Student getStudentByNameAndAverage( String sname, Integer avg);

    public  StudentResponse getStudentById( Integer id);

    public StudentResponse getStudentNameWithYear( Integer id);

    public List<Country> getListOfCountries();
    public Country getCountryByIdInStudent(Integer countryId);






}

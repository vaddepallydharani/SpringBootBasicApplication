package com.example.SpringbootBasicApplication.controller;

import com.example.SpringbootBasicApplication.entity.Country;
import com.example.SpringbootBasicApplication.entity.Student;
import com.example.SpringbootBasicApplication.model.StudentResponse;
import com.example.SpringbootBasicApplication.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/save-students")
    public Student saveStudents(@RequestBody Student student) {
        return studentService.saveStudents(student);
    }

    @GetMapping("/get-all-students")
    public List<Student> getAllStudents(){

        return studentService.getAllStudents();

    }


    @GetMapping("/get-by-name-avg")
    public Student getStudentByNameAndAverage(@RequestParam String sname, @RequestParam Integer avg)
    {
        Student student= studentService.getStudentByNameAndAverage(sname, avg);

        return student;

    }

   @GetMapping("/get-name-sno")
   public StudentResponse getStudentById(@RequestParam Integer id) {

       StudentResponse studentResponse = studentService.getStudentById(id);

       return studentResponse;
   }


    @GetMapping("/get-name-year")
    public StudentResponse getStudentNameWithYear(@RequestParam Integer id) {

        return studentService.getStudentNameWithYear(id) ;
    }

    @GetMapping("/countriesInStudent")
    public List<Country> getListOfCountries(){
        return  studentService.getListOfCountries();
    }

    @GetMapping("/countryByIdInStudents")
    public Country getCountryByIdInStudent(@RequestParam Integer countryId){
        return studentService.getCountryByIdInStudent(countryId);
    }

    @GetMapping("/saddAsHyd")
    public List<Student> findBySAddAsHyd(@RequestParam String sadd){
        return studentService.findBySAddAsHyd(sadd);
    }




}

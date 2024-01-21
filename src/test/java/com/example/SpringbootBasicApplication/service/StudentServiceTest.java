package com.example.SpringbootBasicApplication.service;

import com.example.SpringbootBasicApplication.entity.Country;
import com.example.SpringbootBasicApplication.entity.Student;
import com.example.SpringbootBasicApplication.repository.StudentRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

@ExtendWith(SpringExtension.class)
public class StudentServiceTest {

    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    StudentServiceImpl studentService;

    @Mock
    StudentRepository studentRepository;

    @Test
    public void testSaveStudent(){
        Student student=new Student(1,101,"Thaneesh", "Hyd",99);

        Mockito.when(studentRepository.save(student)).thenReturn(student);

       Student actualStudent= studentService.saveStudents(student);

        Assert.assertEquals(student,actualStudent);
    }

    @Test
    public void testGetAllStudents(){
        Student student1=new Student(1,101,"Thaneesh", "Hyd",99);
        Student student2=new Student(2,102,"junnu", "kurnool",98);
        Student student3=new Student(3,103,"Thanu", "Hyd",100);

        List<Student> studentList=new ArrayList<Student>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);


        Mockito.when(studentRepository.findAll()).thenReturn(studentList);

        List<Student> actualStudents= studentService.getAllStudents();

        Assert.assertEquals(studentList,actualStudents);
    }

    @Test
    public void testGetCountryByIdInStudent(){
        Integer countryId=1;
        Country country=new Country();
        country.setCountryId(countryId);
        country.setCountryName("India");

        String urll = "http://localhost:8080/test";

        Mockito.when(restTemplate.exchange(urll, HttpMethod.GET,null,Country.class)).thenReturn(new ResponseEntity<>(country, HttpStatus.OK));

        Country actualCountry= studentService.getCountryByIdInStudent(countryId);

        Assert.assertEquals(country,actualCountry);
    }




}

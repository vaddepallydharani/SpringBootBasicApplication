package com.example.SpringbootBasicApplication.service;


import com.example.SpringbootBasicApplication.entity.Country;
import com.example.SpringbootBasicApplication.entity.Student;
import com.example.SpringbootBasicApplication.model.StudentResponse;
import com.example.SpringbootBasicApplication.repository.StudentRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.AssertionErrors;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.util.*;

import static org.springframework.test.util.AssertionErrors.assertEquals;

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
        Student student=new Student();
        student.setSname("Alice");

        Mockito.when(studentRepository.save(student)).thenReturn(student);
        Student actualStudent= studentService.saveStudents(student);

        Assert.assertEquals(student,actualStudent);
    }

    @Test
    public void testGetAllStudents(){
        Student student1 = new Student();
        student1.setSname("Alice");
        Student student2 = new Student();
        student2.setSname("Bob");
        List<Student> expectedStudents = Arrays.asList(student1, student2);

        Mockito.when(studentRepository.findAll()).thenReturn(expectedStudents);
        List<Student> actualStudents= studentService.getAllStudents();

        Assert.assertEquals(expectedStudents,actualStudents);
    }

    @Test
    public void testGetStudentByNameAndAverage(){
        Student student=new Student();
        student.setId(1);
        student.setSname("Thaneesh");
        student.setAvg(99);

        Mockito.when(studentRepository.findBySnameAndAvg(student.getSname(), student.getAvg())).thenReturn(student);
        Student actualStudent=studentService.getStudentByNameAndAverage(student.getSname(), student.getAvg());

        Assert.assertEquals(student,actualStudent);
    }

    @Test
    public void testGetStudentById(){
        Integer studentId = 1;
        Student student = new Student();
        student.setId(studentId);
        student.setSname("Thaneesh");

        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        StudentResponse actualStudent=studentService.getStudentById(studentId);

        Assert.assertEquals("Thaneesh",actualStudent.getSname());
    }

    @Test
    public void testGetStudentNameWithYear(){
        Integer studentId = 1;
        Student student= new Student();
        student.setSname("John Doe");

        Mockito.when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        StudentResponse actualStudent= studentService.getStudentNameWithYear(studentId);

        Assert.assertEquals(("John Doe_" + LocalDate.now().getYear()), actualStudent.getSname());
    }


    @Test
    public void testGetCountryByIdInStudent(){
        Integer countryId=52;
        Country country=new Country();
        country.setCountryId(countryId);
        country.setCountryName("Australia");

        String urll = "http://localhost:8080/test";

        Mockito.when(restTemplate.exchange(urll, HttpMethod.GET,null,Country.class)).thenReturn(new ResponseEntity<>(country, HttpStatus.OK));

        Country actualCountry= studentService.getCountryByIdInStudent(countryId);

        Assert.assertEquals(country,actualCountry);
    }

    @Test
    public void testGetListOfCountries(){
        Country country1=new Country();
        country1.setCountryName("India");
        country1.setCountryId(1);
        Country country2=new Country();
        country2.setCountryId(2);
        country2.setCountryName("United States");
        Country country3=new Country();
        country3.setCountryId(52);
        country3.setCountryName("Australia");
        List<Country> expectedCountries=Arrays.asList(country1,country2,country3);

        String urll = "http://localhost:8080/test";

        Mockito.when(restTemplate.exchange(urll,HttpMethod.GET,null, new ParameterizedTypeReference<List<Country>>() {})).thenReturn(new ResponseEntity<>(expectedCountries, HttpStatus.OK));

        List<Country> countries=studentService.getListOfCountries();
        Assert.assertEquals(expectedCountries,countries);

    }



}

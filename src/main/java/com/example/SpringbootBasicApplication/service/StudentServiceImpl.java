package com.example.SpringbootBasicApplication.service;

import com.example.SpringbootBasicApplication.entity.Country;
import com.example.SpringbootBasicApplication.entity.Student;
import com.example.SpringbootBasicApplication.model.StudentResponse;
import com.example.SpringbootBasicApplication.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;


    @Override
    public List<Student> getAllStudents() {

        return studentRepository.findAll();
    }

    @Override
    public Student saveStudents(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentByNameAndAverage(String sname, Integer avg) {

        Student student= studentRepository.findBySnameAndAvg(sname, avg);

        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return student;
    }

    @Override
    public StudentResponse getStudentById(Integer id) {

        Optional<Student> optionalStudent = studentRepository.findById(id);

        Student student = optionalStudent.orElse(null);

        StudentResponse studentResponse = new StudentResponse();
        if(!ObjectUtils.isEmpty(student)) {
            studentResponse.setSno(student.getSno());
            studentResponse.setSname(student.getSname());
        }

        return studentResponse ;
    }

    @Override
    public StudentResponse getStudentNameWithYear(Integer id) {

        Optional<Student> optionalStudent = studentRepository.findById(id);
        Student student = optionalStudent.orElse(null);

        int currentYear = java.time.LocalDate.now().getYear();

        StudentResponse studentResponse = new StudentResponse();

        if(!ObjectUtils.isEmpty(student)) {
            studentResponse.setSname(student.getSname() + "_" + currentYear);
        }

        return studentResponse;
    }

    @Override
    public List<Country> getListOfCountries() {
        String url= "http://localhost:8080/country-state/fetch-all";
       /* HttpHeaders httpHeaders =new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity=new HttpEntity(httpHeaders);*/
        RestTemplate restTemplate=new RestTemplate();
       ResponseEntity<List<Country>> response= restTemplate.exchange(
               url,
               HttpMethod.GET,
               null,
               new ParameterizedTypeReference<List<Country>>(){});
       List<Country> list=response.getBody();
        return list;
    }


    public Country getCountryByIdInStudent(Integer countryId){
        String url= "http://localhost:8080/country-state/get-by-country-id";
        HttpHeaders httpHeaders =new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity=new HttpEntity(httpHeaders);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("countryId",countryId).build();

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<Country> response= restTemplate.exchange(builder.toString(), HttpMethod.GET,httpEntity, Country.class);
        return !ObjectUtils.isEmpty(response) ? response.getBody() : null;
    }

     public List<Student> findBySAddAsHyd( String sadd){
         List<Student> studentList=studentRepository.findBySadd(sadd);

        if(!studentList.isEmpty()){
           /* for(Student student : studentList){
                student.getSadd().equalsIgnoreCase("hyd");}*/

            studentList = studentList.stream()
                    .filter(student -> student.getSadd().equalsIgnoreCase("hyd"))
                    .collect(Collectors.toList());

        }



        return studentList;
    }

}

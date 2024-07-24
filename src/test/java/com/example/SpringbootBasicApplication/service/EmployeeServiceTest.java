package com.example.SpringbootBasicApplication.service;

import com.example.SpringbootBasicApplication.dto.EmployeeDetailsResponse;
import com.example.SpringbootBasicApplication.entity.Employee;
import com.example.SpringbootBasicApplication.model.AddressAndDOB;
import com.example.SpringbootBasicApplication.model.StudentResponse;
import com.example.SpringbootBasicApplication.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    EmployeeServiceImpl employeeService;
    @Mock
    EmployeeRepository employeeRepository;
    @Mock
    RestTemplate restTemplate;

    @Test
    public void testSaveEmployee(){
        Employee employee=new Employee();
        employee.setEmpId(1);
        employee.setEmpName("Thaneesh");
        employee.setEmpSalary(10000);

        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
       Employee actualEmployee= employeeService.saveEmployee(employee);

        assertEquals(employee,actualEmployee);
    }

    @Test
    public void testGetAllEmployees(){
        Employee employee1=new Employee();
        employee1.setEmpName("Thaneesh");
        Employee employee2=new Employee();
        employee2.setEmpName("Bhavishya");
        Employee employee3=new Employee();
        employee3.setEmpName("Junnu");
        List<Employee> employees= Arrays.asList(employee1,employee2,employee3);

        Mockito.when(employeeRepository.findAll()).thenReturn(employees);
        List<Employee> actualEmployees=employeeService.getAllEmployees();

        assertEquals(employees,actualEmployees);
    }

    @Test
    public void testEditEmployee(){
        Employee employee=new Employee();
        employee.setEmpId(1);
        employee.setEmpName("Thaneesh");
        employee.setEmpSalary(10000);

        Mockito.when(employeeRepository.save(employee)).thenReturn(employee);
        Employee actualEmployee= employeeService.editEmployee(employee);

        assertEquals(employee,actualEmployee);
    }

    @Test
    public  void testGet1Emp(){
        Integer empId = 1;
        Employee employee=new Employee();
        employee.setEmpId(empId);
        employee.setEmpName("Thaneesh");
        employee.setEmpSalary(10000);

        Mockito.when(employeeRepository.findById(empId)).thenReturn(Optional.of(employee));
        Employee actualEmployee=employeeService.get1Emp(empId);

        assertEquals(employee,actualEmployee);
    }
    @Test
    public void testGetByEmpName(){
        Employee employee1=new Employee();
        employee1.setEmpName("Thaneesh");
        employee1.setEmpSalary(10000);
        Employee employee2=new Employee();
        employee2.setEmpName("Bhavishya");
        employee2.setEmpSalary(10000);
        Employee employee3=new Employee();
        employee3.setEmpName("Junnu");
        employee3.setEmpSalary(10000);

        Mockito.when(employeeRepository.findByEmpName("Junnu")).thenReturn(Arrays.asList(employee1,employee2,employee3));
        List<Employee> actualEmployee= employeeService.getByEmpName("Junnu");

        assertEquals(Arrays.asList(employee1,employee2,employee3),actualEmployee);
    }

    @Test
    public void testGetByEmpSalary(){
        Employee employee1=new Employee();
        employee1.setEmpName("Thaneesh");
        employee1.setEmpSalary(10000);
        Employee employee2=new Employee();
        employee2.setEmpName("Bhavishya");
        employee2.setEmpSalary(30000);
        Employee employee3=new Employee();
        employee3.setEmpName("Junnu");
        employee3.setEmpSalary(50000);

        Mockito.when(employeeRepository.findByEmpSalary(50000)).thenReturn(Arrays.asList(employee1,employee2,employee3));
        List<Employee> foundEmployees = employeeService.getByEmpSalary(50000);

        assertEquals(Arrays.asList(employee1,employee2,employee3), foundEmployees);

    }

    @Test
    public void testSaveEmployeeFullDetails_WithSuccess() {
        AddressAndDOB addressAndDOB = new AddressAndDOB();
        addressAndDOB.setDateOfBirth("jan 12th");
        addressAndDOB.setStreet("3rdLine");
        Integer empId = 1;
        Employee employee=new Employee();
        employee.setEmpId(empId);
        employee.setEmpName("Thaneesh");
        employee.setEmpSalary(10000);

        EmployeeDetailsResponse employeeDetailsResponse=new EmployeeDetailsResponse();
        employeeDetailsResponse.setEmployee(employee);
        employeeDetailsResponse.setAddressAndDOB(addressAndDOB);

        ResponseEntity<EmployeeDetailsResponse> employeeDetailsResponseResponseEntity = ResponseEntity.ok(employeeDetailsResponse);

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("empId",empId.toString());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AddressAndDOB> httpEntity=new HttpEntity<>(addressAndDOB,httpHeaders);

        Mockito.when(restTemplate.exchange("http://localhost:8080/address", HttpMethod.POST,httpEntity, EmployeeDetailsResponse.class)).thenReturn(employeeDetailsResponseResponseEntity);

        EmployeeDetailsResponse actualResponse = employeeService.saveEmployeeFullDetails(addressAndDOB,empId);

        assertEquals(employeeDetailsResponse, actualResponse);

    }

    @Test
    public void testSaveEmployeeFullDetails_WithException() {
        AddressAndDOB addressAndDOB = new AddressAndDOB();
        addressAndDOB.setDateOfBirth("jan 12th");
        addressAndDOB.setStreet("3rdLine");
        Integer empId = 1;
        Employee employee=new Employee();
        employee.setEmpId(empId);
        employee.setEmpName("Thaneesh");
        employee.setEmpSalary(10000);

        EmployeeDetailsResponse employeeDetailsResponse=new EmployeeDetailsResponse();
        employeeDetailsResponse.setEmployee(employee);
        employeeDetailsResponse.setAddressAndDOB(addressAndDOB);

        ResponseEntity<EmployeeDetailsResponse> employeeDetailsResponseResponseEntity = null;

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("empId",empId.toString());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AddressAndDOB> httpEntity=new HttpEntity<>(addressAndDOB,httpHeaders);

        Mockito.when(restTemplate.exchange("http://localhost:8080/address", HttpMethod.POST,httpEntity, EmployeeDetailsResponse.class)).thenReturn(employeeDetailsResponseResponseEntity);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            employeeService.saveEmployeeFullDetails(addressAndDOB,empId);
        });

        String expectedMessage = "Not Able to save the employee record";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));



    }


}


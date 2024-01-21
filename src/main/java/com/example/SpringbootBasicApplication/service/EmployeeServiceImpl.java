package com.example.SpringbootBasicApplication.service;

import com.example.SpringbootBasicApplication.client.CountriesClient;
import com.example.SpringbootBasicApplication.client.EmployeeClient;
import com.example.SpringbootBasicApplication.client.feign.CountriesFeignClient;
import com.example.SpringbootBasicApplication.dto.EmployeeDetailsResponse;
import com.example.SpringbootBasicApplication.model.AddressAndDOB;
import com.example.SpringbootBasicApplication.entity.Employee;
import com.example.SpringbootBasicApplication.model.Country;
import com.example.SpringbootBasicApplication.model.Districts;
import com.example.SpringbootBasicApplication.model.States;
import com.example.SpringbootBasicApplication.repository.EmployeeRepository;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    EmployeeRepository employeeRepository ;

    @Autowired
    EmployeeClient employeeClient;

    @Autowired
    CountriesClient countriesClient;

    @Autowired
    private CountriesFeignClient countriesFeignClient;

    @Override
    public Employee saveEmployee(Employee employee) {

        return  employeeRepository.save(employee);
    }

    @Override
    public EmployeeDetailsResponse saveEmployeeFullDetails(AddressAndDOB addressAndDOB, Integer empId) {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.add("empId",empId.toString());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AddressAndDOB> httpEntity=new HttpEntity<>(addressAndDOB,httpHeaders);
        ResponseEntity<EmployeeDetailsResponse> responseEntity= restTemplate.exchange("http://localhost:8080/address", HttpMethod.POST,httpEntity, EmployeeDetailsResponse.class);
        return responseEntity.getBody();
    }

    @Override
    public Optional<EmployeeDetailsResponse> getEmployee(Integer empId) throws Exception {

        //Get Address details
        AddressAndDOB addressAndDOB = employeeClient.getEmployeeDetails(empId);

        //Get Employee details
        Optional<Employee> employeeOptional =  employeeRepository.findByEmpId(empId);
        Employee employee = employeeOptional.orElse(null);

        //Get list of countries
        List<Country> countries = countriesClient.getCountries();
        //List<Country> countries = countriesFeignClient.getCountries().getBody();

        if(ObjectUtils.isEmpty(employee)){
            throw new Exception("Not Received employee from DB");
        }


        EmployeeDetailsResponse employeeDetailsResponse = prepareEmployeeDetailsResponse(addressAndDOB, employee, countries);

        return Optional.of(employeeDetailsResponse);
    }

    private static EmployeeDetailsResponse prepareEmployeeDetailsResponse(AddressAndDOB addressAndDOB, Employee employee, List<Country> countries) {
        EmployeeDetailsResponse employeeDetailsResponse = new EmployeeDetailsResponse();
        employeeDetailsResponse.setEmployee(employee);
        employeeDetailsResponse.setAddressAndDOB(addressAndDOB);
        employeeDetailsResponse.setCountries(countries);

        //addressValidated(countries, employeeDetailsResponse);

        isAddressValidated(countries, employeeDetailsResponse);
        return employeeDetailsResponse;
    }

    private static void addressValidated(List<Country> countries, EmployeeDetailsResponse employeeDetailsResponse) {
        List<Country> selectedCountries = countries.stream().filter(country -> country.getCountryName().equalsIgnoreCase("India")).toList();

        selectedCountries.forEach(country -> {
            List<States> states =  country.getStates().stream().filter(state -> state.getStateName().equalsIgnoreCase("Andhra Pradesh")).toList();

            states.forEach(st -> {
                List<Districts> districts =  st.getDistricts().stream().filter(district -> district.getDistrictName().equalsIgnoreCase("Kurnool")).toList();

                employeeDetailsResponse.setIsAddressValidated(!CollectionUtils.isEmpty(districts) ? "TRUE" : "FALSE");

            });

        });
    }

    private static void isAddressValidated(List<Country> countries, EmployeeDetailsResponse employeeDetailsResponse) {
        if (!CollectionUtils.isEmpty(countries)) {
            countries.forEach(country -> {
                if (StringUtils.isNotBlank(country.getCountryName()) && country.getCountryName().equalsIgnoreCase("India")) {
                    if (!CollectionUtils.isEmpty(country.getStates())) {
                        country.getStates().forEach(state -> {
                            if (StringUtils.isNotBlank(state.getStateName()) && state.getStateName().equalsIgnoreCase("Andhra Pradesh")) {
                                if (!CollectionUtils.isEmpty(state.getDistricts())) {
                                    state.getDistricts().forEach(district -> {
                                        if (StringUtils.isNotBlank(district.getDistrictName()) && district.getDistrictName().equalsIgnoreCase("Kurnool")) {
                                            employeeDetailsResponse.setIsAddressValidated("TRUE");
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            });
        }
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee editEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee get1Emp(Integer empId) {
        return employeeRepository.findById(empId).isPresent() ? employeeRepository.findById(empId).get() : null;
    }

    @Override
    public List<Employee> getByEmpName(String empName) {

        return employeeRepository.findByEmpName( empName);
    }

    @Override
    public List<Employee> getByEmpSalary(Integer empSalary) {

        return employeeRepository.findByEmpSalary(empSalary);
    }

    @Override
    public String deleteEmployee(Integer empId) {
       employeeRepository.deleteById(empId);
        return "Deleted successfully";
    }


}

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
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

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
        HttpEntity<AddressAndDOB> httpEntity=new HttpEntity<AddressAndDOB>(addressAndDOB,httpHeaders);
        ResponseEntity<EmployeeDetailsResponse> responseEntity= restTemplate.exchange("http://localhost:8080/address", HttpMethod.POST,httpEntity, EmployeeDetailsResponse.class);
        return responseEntity.getBody();
    }

    @Override
    public Optional<EmployeeDetailsResponse> getEmployee(Integer empId) throws Exception {

        //Get Address details
        AddressAndDOB addressAndDOB = employeeClient.getEmployeeDetails(empId);

        //Get Employee details
        Optional<Employee> employeeOptional =  employeeRepository.findByEmpId(empId);
        Employee employee = employeeOptional.get();

        //Get list of countries
        List<Country> countries = countriesClient.getCountries();
        //List<Country> countries = countriesFeignClient.getCountries().getBody();

        if(ObjectUtils.isEmpty(employee)){
            throw new Exception("Not Received employee from DB");
        }


        EmployeeDetailsResponse employeeDetailsResponse = new EmployeeDetailsResponse();
        employeeDetailsResponse.setEmployee(employee);
        employeeDetailsResponse.setAddressAndDOB(addressAndDOB);
        employeeDetailsResponse.setCountries(countries);

        List<Country> selectedCountries = countries.stream().filter(country -> country.getCountryName().equalsIgnoreCase("India")).collect(Collectors.toList());

        selectedCountries.forEach(country -> {
            List<States> states =  country.getStates().stream().filter(state -> state.getStateName().equalsIgnoreCase("Andhra Pradesh")).collect(Collectors.toList());

            states.forEach(st -> {
                List<Districts> districts =  st.getDistricts().stream().filter(district -> district.getDistrictName().equalsIgnoreCase("Kurnool")).collect(Collectors.toList());

                employeeDetailsResponse.setIsAddressValidated(!CollectionUtils.isEmpty(districts) ? "TRUE" : "FALSE");

            });

        });

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


       /* for(Country country:countries){
            if(country.getCountryName().equalsIgnoreCase("India")){
                for(States state: country.states){
                    if(state.getStateName().equalsIgnoreCase("Andhra Pradesh")){
                        for(Districts district: state.districts){
                            if(district.getDistrictName().equalsIgnoreCase("Kurnool")){
                                System.out.println("Kurnool is present in AP and AP is in India");
                                employeeDetailsResponse.setIsAddressValidated("TRUE");
                            }
                        }
                    }
                }
            }

        }*/



        return Optional.of(employeeDetailsResponse);
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
        return employeeRepository.findById(empId).get();
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

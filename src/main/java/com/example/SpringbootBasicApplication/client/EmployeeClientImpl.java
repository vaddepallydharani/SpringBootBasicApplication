package com.example.SpringbootBasicApplication.client;

import com.example.SpringbootBasicApplication.model.AddressAndDOB;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class EmployeeClientImpl implements EmployeeClient{
    @Override
    public AddressAndDOB getEmployeeDetails(Integer empId) {

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity=new HttpEntity(httpHeaders);

        //Setting request params
        String url = "http://localhost:8080/address";
        final RestTemplate restTemplate = new RestTemplate();

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("empId",empId).build();

        ResponseEntity<AddressAndDOB> response= restTemplate.exchange(builder.toString(), HttpMethod.GET,httpEntity,AddressAndDOB.class);
        AddressAndDOB address = response.getBody();

        return address;
    }
}

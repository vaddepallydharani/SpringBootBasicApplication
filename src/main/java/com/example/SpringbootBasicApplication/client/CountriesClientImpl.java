package com.example.SpringbootBasicApplication.client;

import com.example.SpringbootBasicApplication.model.Country;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CountriesClientImpl implements CountriesClient{
    @Override
    public List<Country> getCountries() {

        final RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Country>> countriesResponse = restTemplate.exchange("http://localhost:8080/country-state/fetch-all", HttpMethod.GET,null,new ParameterizedTypeReference<List<Country>>(){});
        List<Country> countries=countriesResponse.getBody();

        return countries;
    }
}

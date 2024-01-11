package com.example.SpringbootBasicApplication.client.feign;

import com.example.SpringbootBasicApplication.model.Country;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "countriesFeignClient", url = "http://localhost:8080", path = "/country-state")
public interface CountriesFeignClient {

    @GetMapping("/fetch-all")
    ResponseEntity<List<Country>> getCountries();
}

package com.example.SpringbootBasicApplication.client;

import com.example.SpringbootBasicApplication.model.Country;

import java.util.List;

public interface CountriesClient {
    List<Country> getCountries();
}

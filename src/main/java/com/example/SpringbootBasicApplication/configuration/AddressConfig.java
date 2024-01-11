package com.example.SpringbootBasicApplication.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AddressConfig {
    @Bean
    public ModelMapper modelMapperBean() {
        return new ModelMapper();
    }
}

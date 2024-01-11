package com.example.SpringbootBasicApplication;

import org.springframework.stereotype.Service;

@Service
public class RedirectService {

    public String redirectServiceUrl(String resourceType, String sorId){

        return "https://www.google.com";

    }
}

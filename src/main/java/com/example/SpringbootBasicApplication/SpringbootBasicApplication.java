package com.example.SpringbootBasicApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients("com.example.SpringbootBasicApplication.client.feign")
//@ImportAutoConfiguration({FeignAutoConfiguration.class, HttpClientConfiguration.class})
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class SpringbootBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBasicApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate(){
		return  new RestTemplate();
	}
}

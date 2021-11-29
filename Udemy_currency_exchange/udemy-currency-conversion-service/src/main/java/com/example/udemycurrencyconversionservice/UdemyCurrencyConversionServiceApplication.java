package com.example.udemycurrencyconversionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UdemyCurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyCurrencyConversionServiceApplication.class, args);
	}

}

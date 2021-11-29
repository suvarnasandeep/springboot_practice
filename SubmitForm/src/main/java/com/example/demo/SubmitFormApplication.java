package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

//https://www.youtube.com/watch?v=UfOxcrxhC0s

@ComponentScan
@SpringBootApplication
public class SubmitFormApplication extends SpringBootServletInitializer{

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SubmitFormApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SubmitFormApplication.class, args);
	}

}

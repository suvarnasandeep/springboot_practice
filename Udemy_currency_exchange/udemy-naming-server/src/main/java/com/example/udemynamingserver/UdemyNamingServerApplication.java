package com.example.udemynamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class UdemyNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UdemyNamingServerApplication.class, args);
	}

}

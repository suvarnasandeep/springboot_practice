package com.programmingtechie.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//https://www.youtube.com/watch?v=0TQliqoX6Kc&list=PLSVW22jAG8pBnhAdq9S8BpLnZ0_jVBj0c&index=3
//https://github.com/SaiUpadhyayula/spring-boot-microservices-new/tree/master/order-service/src/main

@SpringBootApplication
@EnableEurekaClient
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}

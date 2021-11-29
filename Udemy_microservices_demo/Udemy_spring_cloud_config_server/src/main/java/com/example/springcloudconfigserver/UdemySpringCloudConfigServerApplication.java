package com.example.springcloudconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class UdemySpringCloudConfigServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(UdemySpringCloudConfigServerApplication.class, args);
	}

}

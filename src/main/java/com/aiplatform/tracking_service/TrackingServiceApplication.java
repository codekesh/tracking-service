package com.aiplatform.tracking_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrackingServiceApplication {

	public static void main(String[] args) {
		System.out.print("###############################");
		System.out.println(System.getProperty("DB_URL"));
		System.out.print("###############################");
		SpringApplication.run(TrackingServiceApplication.class, args);
	}

}

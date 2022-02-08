package com.wasathDev.LearingSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class LearingSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearingSpringApplication.class, args);
	}

}

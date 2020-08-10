package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"your.company.domain.package"})
public class Covid19TestRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19TestRegistrationApplication.class, args);
	}

}

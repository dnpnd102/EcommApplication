package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@EnableJpaRepositories("com.example.demo.model.persistence.repositories")
@EntityScan("com.example.demo.model.persistence")
@SpringBootApplication(scanBasePackages = "com.example.demo")

public class SareetaApplication  {

	
	public static void main(String[] args)  {
		SpringApplication.run(SareetaApplication.class, args);
	}
	

}

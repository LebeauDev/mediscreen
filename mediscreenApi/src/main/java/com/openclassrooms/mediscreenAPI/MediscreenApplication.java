package com.openclassrooms.mediscreenAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.openclassrooms.mediscreenAPI.model.Patient;
import com.openclassrooms.mediscreenAPI.repository.PatientRepository;

@SpringBootApplication
public class MediscreenApplication {
	
	private final Logger logger = LoggerFactory.getLogger(MediscreenApplication.class);
	
	public static void main(String[] args) {
		
		SpringApplication.run(MediscreenApplication.class, args);
	}


	
	

}

package com.openclassrooms.mediscreenNote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.openclassrooms.mediscreenNote.repository.NoteRepository;

@SpringBootApplication
public class MediscreenNoteApplication {
	
	private final Logger logger = LoggerFactory.getLogger(MediscreenNoteApplication.class);

	@Autowired
	NoteRepository noteRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(MediscreenNoteApplication.class, args);
	}


	
	

}

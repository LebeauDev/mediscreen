package com.openclassrooms.mediscreenRisque.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.openclassrooms.mediscreenRisque.entity.Patient;

@Service
public class PatientService {

	public int age(Patient p) {
		
		
		int dob = Integer.parseInt(p.getDob().split("-")[0]);
		
		int age = 2022 - dob;
		
		return age;
	}
}

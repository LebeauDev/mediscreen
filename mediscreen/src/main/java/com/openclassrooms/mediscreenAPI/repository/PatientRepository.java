package com.openclassrooms.mediscreenAPI.repository;

import org.springframework.data.repository.CrudRepository;

import com.openclassrooms.mediscreenAPI.model.Patient;


public interface PatientRepository extends CrudRepository<Patient, Long> {

	Patient findByFamilyAndGiven(String family, String given);

	Patient getById(int parseInt);
	
	Patient getByFamily(String family);

	
}



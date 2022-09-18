package com.openclassrooms.mediscreen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import com.openclassrooms.mediscreen.model.Patient;


public interface PatientRepository extends CrudRepository<Patient, Long> {

	Patient findByFamilyAndGiven(String family, String given);

	Patient getById(int parseInt);

	
	/*
	Patient findByFamilyGiven(String family, String given);
	
	List<Patient> findAllPatient();
	
	boolean deletePatient(Patient patient);
	
	boolean addPatient (Patient patient);
	
	Patient updatePatient(Patient patient);
	
	Patient findIdByFamilyGiven(String family, String given);

	Patient findById(String id);
	*/
}



package com.openclassrooms.mediscreenAPI.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.openclassrooms.mediscreenAPI.model.Patient;
import com.openclassrooms.mediscreenAPI.repository.PatientRepository;

@Controller
public class PatientController {

	@Autowired
	PatientRepository patientRepo;
	
	

	
	@RequestMapping(value = { "/patient" }, method = RequestMethod.GET)
	public ResponseEntity<?> getPatient(@RequestParam Map<String, String> allRequestParams) {
		
		System.out.println(allRequestParams);
		
		if (!allRequestParams.containsKey("family") || !allRequestParams.containsKey("given"))
			return (ResponseEntity<?>) ResponseEntity.internalServerError();
			
		
		String family = allRequestParams.get("family");
		String given = allRequestParams.get("given");
		Patient patient = patientRepo.findByFamilyAndGiven(family, given);

		return ResponseEntity.ok(patient);
		 
		
	}
	
	@RequestMapping(value = {"/allPatients"}, method = RequestMethod.GET)
	
	public ResponseEntity<?> getAllPatients(@RequestParam Map<String, String> allRequestParams){
		
		List<Patient> listPatient = (List<Patient>) patientRepo.findAll();
		
		return ResponseEntity.ok(listPatient);
		
		
	}
	
	@RequestMapping(value = "/patient/")
	public ResponseEntity<?> getPatientById(@RequestParam Map<String, String> allRequestParams){
		
		String id = allRequestParams.get("id");
		
		Patient patient = patientRepo.getById(Integer.parseInt(id));

		return ResponseEntity.ok(patient);
	
	}
	
	@RequestMapping(value = "/patientName/")
	public ResponseEntity<?> getPatientByFamily(@RequestParam Map<String, String> allRequestParams){
		
		String family = allRequestParams.get("family");
		
		Patient patient = patientRepo.getByFamily(family);

		return ResponseEntity.ok(patient);
	
	}
	
	@RequestMapping(value = {"/delete"}, method = RequestMethod.POST)
	public ResponseEntity<?>  deletePatient(@RequestParam Map<String, String> allRequestParams){
		
		String family = allRequestParams.get("family");
		String given = allRequestParams.get("given");
		
		Patient patient = new Patient();
		
		patient = patientRepo.findByFamilyAndGiven(family, given);
		
		patientRepo.delete(patient);
		
		return ResponseEntity.ok("OK");
	}
	
	@RequestMapping(value = {"/addPatient"}, method = RequestMethod.POST)
	public ResponseEntity<String> addPatient(@RequestParam Map<String, String> allRequestParams){
		
		
		if (!allRequestParams.containsKey("family") || !allRequestParams.containsKey("given") || !allRequestParams.containsKey("dob") 
				|| !allRequestParams.containsKey("sex") || !allRequestParams.containsKey("phone") || !allRequestParams.containsKey("address"))
			return ResponseEntity.badRequest().body("Mauvais paramètres");
		
		
		String family = allRequestParams.get("family");
		String given = allRequestParams.get("given");
		String dob = allRequestParams.get("dob");
		String sex = allRequestParams.get("sex");
		String phone = allRequestParams.get("phone");
		String adress = allRequestParams.get("address");
		
		
		Patient patient = new Patient();
		patient.setFamily(family);
		patient.setGiven(given);
		patient.setDob(dob);
		patient.setSex(sex);
		patient.setPhone(phone);
		patient.setAddress(adress);
		
		
		
		if(patientRepo.save(patient) != null) {
			return ResponseEntity.ok("OK");
		}
		
				
		return ResponseEntity.badRequest().body("KO");
	}
	
	
	@RequestMapping(value = {"/updatePatient"})
	public ResponseEntity<String> updatePatient(@RequestParam Map<String, String> allRequestParams) {
		
		if (!allRequestParams.containsKey("family") || !allRequestParams.containsKey("given"))
			return ResponseEntity.badRequest().body("Mauvais paramètres");
		
		String family = allRequestParams.get("family");
		String given = allRequestParams.get("given");
		String dob = allRequestParams.get("dob");
		String sex = allRequestParams.get("sex");
		String phone = allRequestParams.get("phone");
		String adress = allRequestParams.get("address");
		
		Patient patient = patientRepo.findByFamilyAndGiven(family, given);
		
		
		
		patient.setDob(dob);
		patient.setSex(sex);
		patient.setPhone(phone);
		patient.setAddress(adress);
		
		patient = patientRepo.save(patient);
		
		
		
		
		return ResponseEntity.ok("UPDATE OK");
	}
	

}

package com.openclassrooms.mediscreenNote.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mediscreenNote.model.Note;
import com.openclassrooms.mediscreenNote.repository.NoteRepository;



@RestController
public class PatientController {

	@Autowired
	NoteRepository noteRepo;

//	@RequestMapping(value = { "/patient/add" }, method = RequestMethod.PUT)
//	public ResponseEntity<?> patientAdd(@RequestParam Map<String, String> allRequestParams) {
//
//		String family = allRequestParams.get("family");
//		String given = allRequestParams.get("given");
//		String dob = allRequestParams.get("dob");
//		String sex = allRequestParams.get("sex");
//		String address = allRequestParams.get("address");
//		String phone = allRequestParams.get("phone");
//
//		Patient patient = new Patient();
//
//		patient.setFamily(family);
//		patient.setGiven(given);
//		patient.setDob(dob);
//		patient.setSex(sex);
//		patient.setAddress(address);
//		patient.setPhone(phone);
//
//		//patientRepo.save(patient);
//
//		return ResponseEntity.ok(patient);
//	}
//
//	@RequestMapping(value = { "/patient/update" }, method = RequestMethod.POST)
//	public ResponseEntity<?> patientUpdate(@RequestParam Map<String, String> allRequestParams) {
//
//		String id = allRequestParams.get("id");
//
//		String family = allRequestParams.get("family");
//		String given = allRequestParams.get("given");
//		String dob = allRequestParams.get("dob");
//		String sex = allRequestParams.get("sex");
//		String address = allRequestParams.get("address");
//		String phone = allRequestParams.get("phone");
//		
//		/*
//		Patient p = new Patient();
//		p.setFamily(family);
//		p.setGiven(given);
//
//		Example<Patient> exampleP = Example.of(p);
//
//		//Patient patient = patientRepo.findOne(exampleP).get();
//
//		 Patient patient = patientRepo.findById(id).get();
//		 
//		 */
//		
//		//Patient patient = patientRepo.findById(id).get();
//		
//		
//
//		//System.out.println("######" + patient.getSex());
//
//		patient.setFamily(family);
//		patient.setGiven(given);
//		patient.setDob(dob);
//		patient.setSex(sex);
//		patient.setAddress(address);
//		patient.setPhone(phone);
//		
//		//patientRepo.save(patient);
//
//		return ResponseEntity.ok(patient);
//	}
//
//	@RequestMapping(value = { "/patient/delete" }, method = RequestMethod.DELETE)
//	public ResponseEntity<?> patientDelete(@RequestParam Map<String, String> allRequestParams) {
//		
//		
//		Patient existingPatient = new Patient();
//		String id = allRequestParams.get("id");
//
//
//		//Patient patient = patientRepo.findById(id).get();
//
//		
//		//patientRepo.delete(patient);
//		
//		//patientRepo.delete(patient);
//
//		return ResponseEntity.ok("Success !");
//	}
	
	
	/*
	@RequestMapping(value = { "/patient" }, method = RequestMethod.GET)
	public ResponseEntity<?> getPatient(@RequestParam Map<String, String> allRequestParams) {
		
		System.out.println(allRequestParams);
		
		if (!allRequestParams.containsKey("family") || !allRequestParams.containsKey("given"))
			return (ResponseEntity<?>) ResponseEntity.internalServerError();
			//return "Mauvais parametres";
		
		String family = allRequestParams.get("family");
		String given = allRequestParams.get("given");
		Patient patient = patientRepo.findByFamilyGiven(family, given);

		return ResponseEntity.ok(patient);
		//return patient.toString(); 
		
	}
	*/
	
	@RequestMapping(value = {"/allNotes"}, method = RequestMethod.GET)
	//public String getAllPatients(@RequestParam Map<String, String> allRequestParams){
	public ResponseEntity<?> getAllPatients(@RequestParam Map<String, String> allRequestParams){
		
		List<Note> listPatient = noteRepo.findAllNote();
		
		return  ResponseEntity.ok(listPatient);
		
		//return listPatient.toString();
	}
	
	@RequestMapping(value = {"/notes"}, method = RequestMethod.GET)
	//public String getAllPatients(@RequestParam Map<String, String> allRequestParams){
	public ResponseEntity<?> getNotePatient(@RequestParam Map<String, String> allRequestParams){
		
		int id = Integer.parseInt(allRequestParams.get("idPatient"));
		
		List<Note> listPatient = noteRepo.findAllNote();
		
		return  ResponseEntity.ok(listPatient);
		
		//return listPatient.toString();
	}
	
	/*@RequestMapping(value = "/patient/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable String id){
		
		Patient patient = patientRepo.findById(id).get();

		return ResponseEntity.ok(patient);
	
	}*/
	
	/*
	@RequestMapping(value = {"/delete"}, method = RequestMethod.POST)
	public String deletePatient(@RequestParam Map<String, String> allRequestParams){
		
		String family = allRequestParams.get("family");
		String given = allRequestParams.get("given");
		
		Patient patient = new Patient();
		
		patient = patientRepo.findByFamilyGiven(family, given);
		
		patientRepo.deletePatient(patient);
		
		return "OK";
	}
	*/
	
	@RequestMapping(value = {"/addNote"})
	public ResponseEntity<?> addNote(@RequestParam Map<String, String> allRequestParams){
		
		
		if (!allRequestParams.containsKey("note") || !allRequestParams.containsKey("idPatient") || !allRequestParams.containsKey("date"))
			return ResponseEntity.badRequest().body("Mauvais paramètres");
		
		
		String note = allRequestParams.get("note");
		int idPatient = Integer.parseInt(allRequestParams.get("idPatient"));
		String date = allRequestParams.get("date");
		
		
		Note patientNote = new Note();
		
		patientNote.setNote(note);
		
		patientNote.setIdPatient(idPatient);
		
		patientNote.setDate(date);
		
		try {
			noteRepo.addNote(patientNote);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
				
		return ResponseEntity.ok("ADDED OK");
	}
	
	@RequestMapping(value = {"/updateNote"})
	public ResponseEntity<?> updateNote(@RequestParam Map<String, String> allRequestParams){
		
		
		if (!allRequestParams.containsKey("note") || !allRequestParams.containsKey("id") || !allRequestParams.containsKey("date"))
			return ResponseEntity.badRequest().body("Mauvais paramètres");
		
		
		String note = allRequestParams.get("note");
		int idPatient = Integer.parseInt(allRequestParams.get("idPatient"));
		String date = allRequestParams.get("date");
		String id = allRequestParams.get("id");
		
		Note patientNote = noteRepo.findNoteById(id);
		
		patientNote.setNote(note);
		
		patientNote.setDate(date);
		
		try {
			noteRepo.saveNote(patientNote);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
				
		return ResponseEntity.ok("UPDATE OK");
	}
	
	
	/*
	@RequestMapping(value = {"/updatePatient"})
	public String updatePatient(@RequestParam Map<String, String> allRequestParams) {
		
		if (!allRequestParams.containsKey("family") || !allRequestParams.containsKey("given"))
			return "Mauvais parametres";
		
		String family = allRequestParams.get("family");
		String given = allRequestParams.get("given");
		String dob = allRequestParams.get("dob");
		String sex = allRequestParams.get("sex");
		String phone = allRequestParams.get("phone");
		String adress = allRequestParams.get("address");
		
		Patient patient = patientRepo.findByFamilyGiven(family, given);
		
		patient.setDob(dob);
		patient.setSex(sex);
		patient.setPhone(phone);
		patient.setAddress(adress);
		
		patient = patientRepo.updatePatient(patient);
		
		
		return "UPDATE OK";
	}
	*/
	

}

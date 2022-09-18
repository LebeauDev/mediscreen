package com.openclassrooms.mediscreenNote.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import com.mongodb.client.result.DeleteResult;
import com.openclassrooms.mediscreenNote.model.Note;

@Repository
public class NoteRepositoryImpl implements NoteRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	/*
	public Patient findByFamilyGiven (String familyParam, String givenParam) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("family").is(familyParam));
		query.addCriteria(Criteria.where("given").is(givenParam));
		Patient patient = mongoTemplate.findOne(query, Patient.class);
		return patient;
		
		
	}
	*/
	
	public List<Note> findAllNote(){
		
		List <Note> patients = mongoTemplate.findAll(Note.class);
		
		return patients;
	}
	
	@Override
	public List<Note> findAllNoteById(int id) {
		// TODO Auto-generated method stub
		
		Query query = new Query();
		query.addCriteria(Criteria.where("idPatient").is(id));
		List <Note> patients  = (List<Note>) mongoTemplate.find(query, Note.class);
		
		
		return patients;
	}
	
	@Override
	public Note findNoteById(String id) {
		// TODO Auto-generated method stub
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		Note patients  = (Note) mongoTemplate.findOne(query,  Note.class);
		
		
		return patients;
	}
	

	/*
	@Override
	public boolean deletePatient(Patient patient) {
		
		DeleteResult res = mongoTemplate.remove(patient);
		if (res.getDeletedCount() == 1) {
			return true;
		} else {
			return false;
		}
		
	}
	
	*/
	
	public boolean addNote(Note note) {
		
		Note noteInsert = mongoTemplate.insert(note);
		
		if(noteInsert != null) {
			return true;
		}else {
			return false;
		}
	}
	
public boolean saveNote(Note note) {
		
		Note noteInsert = mongoTemplate.save(note);
		
		if(noteInsert != null) {
			return true;
		}else {
			return false;
		}
	}




	/*
	public Patient updatePatient(Patient patient) {
		
		
		Patient patientUpdate = mongoTemplate.save(patient);
		
		
		
		return patientUpdate;
	}
	
	*/
}

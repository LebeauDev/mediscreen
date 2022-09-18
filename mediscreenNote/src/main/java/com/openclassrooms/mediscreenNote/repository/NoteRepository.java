package com.openclassrooms.mediscreenNote.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mediscreenNote.model.Note;


@Repository
public interface NoteRepository {
	
	//Patient findByFamilyGiven(String family, String given);
	
	List<Note> findAllNote();
	
	List<Note> findAllNoteById(int id);
	
	Note findNoteById(String id);
	
	//boolean deletePatient(Patient patient);
	
	boolean addNote (Note note);
	
	boolean saveNote (Note note);
	
	//Patient updatePatient(Patient patient);
}



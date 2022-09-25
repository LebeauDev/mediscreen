package com.openclassrooms.mediscreenNote.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.openclassrooms.mediscreenNote.model.Note;


@Repository
public interface NoteRepository {
	
	
	List<Note> findAllNote();
	
	List<Note> findAllNoteById(int id);
	
	Note findNoteById(String id);
	
	
	
	boolean addNote (Note note);
	
	boolean saveNote (Note note);

}



package com.openclassrooms.mediscreenNote.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

	@RequestMapping(value = { "/allNotes" }, method = RequestMethod.GET)

	public ResponseEntity<?> getAllPatients(@RequestParam Map<String, String> allRequestParams) {

		List<Note> listPatient = noteRepo.findAllNote();

		return ResponseEntity.ok(listPatient);

	}

	@RequestMapping(value = { "/note" }, method = RequestMethod.GET)

	public ResponseEntity<?> getNote(@RequestParam Map<String, String> allRequestParams) {

		String id = allRequestParams.get("id");

		Note note = noteRepo.findNoteById(id);

		return ResponseEntity.ok(note);

	}

	@RequestMapping(value = { "/notes" }, method = RequestMethod.GET)

	public ResponseEntity<?> getNotePatient(@RequestParam Map<String, String> allRequestParams) {

		int id = Integer.parseInt(allRequestParams.get("idPatient"));

		List<Note> listPatient = noteRepo.findAllNoteById(id);

		return ResponseEntity.ok(listPatient);

	}

	@RequestMapping(value = { "/patHistory/add" })
	public ResponseEntity<?> addNote(@RequestParam Map<String, String> allRequestParams) {

		String note = allRequestParams.get("e");
		int idPatient = Integer.parseInt(allRequestParams.get("patId"));
		Date date1 = new Date();

		String date = date1.toString();

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

	@RequestMapping(value = { "/updateNote" }, method = RequestMethod.POST)
	public ResponseEntity<?> updateNote(@RequestParam Map<String, String> allRequestParams) {

		if (!allRequestParams.containsKey("note") || !allRequestParams.containsKey("id")
				|| !allRequestParams.containsKey("date"))
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

}

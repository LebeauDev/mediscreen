package com.openclassrooms.mediscreenRisque.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.openclassrooms.mediscreenRisque.entity.Note;
import com.openclassrooms.mediscreenRisque.entity.Patient;

@Service
public class NoteService {

	public String risque(List<Note> listNote, int age, Patient p) {
		
		String assess = "";
		 
		String notes = " ";
		
		for (Note note : listNote) {
			notes = notes + note.getNote();
		}
		
		int count = 0;
		
		List<String> listDecelencheur = new ArrayList<String>();
		
		listDecelencheur.add("Hémoglobine A1C");
		listDecelencheur.add("Microalbumine");
		listDecelencheur.add("Taille");
		listDecelencheur.add("Poids");
		listDecelencheur.add("Fumeur");
		listDecelencheur.add("Anormal");
		listDecelencheur.add("Cholestérol");
		listDecelencheur.add("Vertige");
		listDecelencheur.add("Rechute");
		listDecelencheur.add("Réaction");
		listDecelencheur.add("Anticorps");
		
		for (String decl : listDecelencheur) {
			if(notes.toLowerCase().contains(decl.toLowerCase())) {
				
				count = count + 1;
			}
		}
		
		
		

		if(count == 0) {
			System.out.println("&&&&&&&& 0, "+count);
			assess = "None";

		}
		if(count == 1) {
			System.out.println("&&&&&&&& 0, "+count);
			assess = "None";

		}
		if(count >= 2) {
			System.out.println("&&&&&&&& 2, "+count);
			if(age > 30) assess = "Borderline";

		}
		if(count >= 3) {
			System.out.println("&&&&&&&& 3, "+count);
			if(age < 30 && p.getSex() == "M") assess = "In Danger";

		}
		if(count >= 4) {
			System.out.println("&&&&&&&& 4, "+count);
			if(age < 30 && p.getSex() == "F") assess = "In Danger";

		}
		if(count >= 5) {
			System.out.println("&&&&&&&& 5, "+count);
			if(age < 30 && p.getSex() == "M") assess = "Early onset";
			

		}
		if(count >= 6) {
			System.out.println("&&&&&&&& 6, "+count);
			if(age > 30) assess = "In Danger";
		}
		if(count >= 7) {
			System.out.println("&&&&&&&& 7, "+count);
			if(age < 30 && p.getSex() == "F") assess =  "Early onset";
		}	
		
		if(count >= 8 && age > 30)   assess =  "Early onset";
		
		System.out.println("&&&&&&&& "+count);
		
		
		return "Patient : " + p.getFamily() + " " + p.getGiven() + "(age " + age + " )" + " diabetes assessment is:" + assess ;
	}
}

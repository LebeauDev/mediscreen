package com.openclassrooms.mediscreenFront.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassromms.mediscreenFront.form.NoteForm;
import com.openclassromms.mediscreenFront.form.PatientForm;
import com.openclassrooms.mediscreenFront.model.Note;
import com.openclassrooms.mediscreenFront.model.Patient;

@Controller
public class NoteController {
	
	@GetMapping({ "/listNote" })
	public ModelAndView showUsers(@RequestParam MultiValueMap<String, String> param) {
		
		String id = param.getFirst("idPatient");
		
		//System.out.println("+++++"+id);

		WebClient client = WebClient.create();

		WebClient.ResponseSpec responseSpec = client.get().uri("http://localhost:8082/notes?idPatient="+id).retrieve();

		String responseBody = responseSpec.bodyToMono(String.class).block();
		
		
		

		ObjectMapper mapper = new ObjectMapper();

		try {

			List<Note> listNote = mapper.readValue(responseBody, new TypeReference<List<Note>>() {
			});

			try {
				ModelAndView mav = new ModelAndView("listNote");

				mav.addObject("listNote", listNote);

				return mav;
			} catch (Exception e) {
				// TODO: handle exception
				ModelAndView mav = new ModelAndView("login");
				return mav;
			}

		} catch (JsonMappingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;

	}

	// remplace postman pour requette vers API POST /add plus
	@GetMapping({ "/addNote" })
	public ModelAndView addNote() {
		ModelAndView mav = new ModelAndView("addNote");

		NoteForm noteForm = new NoteForm();

		mav.addObject("noteForm", noteForm);

		return mav;
	}
	
	
	// API mais aul lieu de repository, requette vers une autre API
	@RequestMapping(value = { "/addNote" }, method = RequestMethod.POST)
	public String addPatient(Model model, @ModelAttribute("noteForm") NoteForm noteForm) {

		String note = noteForm.getNote();
		int idPatient = noteForm.getIdPatient();
		String date = noteForm.getDate();

		MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

		bodyValues.add("note", note);
		bodyValues.add("idPatient", ""+idPatient);
		bodyValues.add("date", date);
		//bodyValues.add("sex", sex);
		//bodyValues.add("address", address);
		//bodyValues.add("phone", phone);

		WebClient client = WebClient.create();

		try {

			String response = client.post().uri(new URI("http://localhost:8082/addNote"))
					.body(BodyInserters.fromFormData(bodyValues)).retrieve().bodyToMono(String.class).block();
			System.out.println(response);
			return "redirect:/listPatient";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "addNote";
	}
	
	/*

	@GetMapping({"/delete"})
	public ModelAndView deleteUser() {
		ModelAndView mav = new ModelAndView("deletePatient");

		PatientForm patientForm = new PatientForm();

		mav.addObject("patientForm", patientForm);

		return mav;
	}

	@RequestMapping(value = { "/delete" }, method = RequestMethod.POST)
	public String deletePatient(Model model, @ModelAttribute("patientForm") PatientForm patientForm) {

		String family = patientForm.getFamily();
		String given = patientForm.getGiven();
		    
		MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

		bodyValues.add("family", family);
		bodyValues.add("given", given);
		
		WebClient client = WebClient.create();
		
		

		try {

			String response = client.post()
					.uri(new URI("http://localhost:8080/delete"))
					.body(BodyInserters.fromFormData(bodyValues))
					.retrieve()
					.bodyToMono(String.class)
					.block();
			System.out.println(response);
			return "redirect:/listPatient";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "deletePatient";
	}
	
	*/
	@GetMapping({ "/updateNote" })
	public ModelAndView updatePatient(@RequestParam MultiValueMap<String, String> param) {
		ModelAndView mav = new ModelAndView("updateNote");
		
		String id = param.getFirst("id");
		
		WebClient client = WebClient.create();

		WebClient.ResponseSpec responseSpec = client.get().uri("http://localhost:8082/note/?id="+id).retrieve();
		
		//System.out.println("http://localhost:8080/patient/?family = "+family+"&given="+given);
		
		String responseBody = responseSpec.bodyToMono(String.class).block();

		ObjectMapper mapper = new ObjectMapper();

		NoteForm noteForm = new NoteForm();

		try {
			Note n = mapper.readValue(responseBody, new TypeReference<Note>() {});
			
			noteForm.setDate(n.getDate());
			noteForm.setId(n.getId());
			noteForm.setIdPatient(n.getIdPatient());
			noteForm.setNote(n.getNote());
			
			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		mav.addObject("noteForm", noteForm);

		return mav;
	}
	
	

	@RequestMapping(value = { "/updateNote" }, method = RequestMethod.POST)
	public String updatePatient(Model model, @ModelAttribute("noteForm") NoteForm noteForm) {

		String note = noteForm.getNote();
		String id =  noteForm.getId();
		String date  =  noteForm.getDate();
		int idPatient =  noteForm.getIdPatient();
		
		System.out.println("++++++++ "+idPatient);
		

		MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

		bodyValues.add("note", note);
		bodyValues.add("id", id);
		bodyValues.add("date", date);
		bodyValues.add("idPatient", idPatient+"");

		WebClient client = WebClient.create();

		try {
			
			

			String response = client.post().uri(new URI("http://localhost:8082/updateNote"))
					.body(BodyInserters.fromFormData(bodyValues)).retrieve().bodyToMono(String.class).block();
			//System.out.println("u "+response);
			return "redirect:/listNote?idPatient="+idPatient;
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "updatePatient";
	}
	
	
	
}

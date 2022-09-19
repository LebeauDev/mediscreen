package com.openclassrooms.mediscreenRisque.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.mediscreenRisque.entity.Note;
import com.openclassrooms.mediscreenRisque.entity.Patient;
import com.openclassrooms.mediscreenRisque.service.NoteService;
import com.openclassrooms.mediscreenRisque.service.PatientService;


@Controller
public class MediscreenRiqueController {
	
	@Autowired
	PatientService patientServ;
	
	@Autowired
	NoteService noteServ;
	
	@RequestMapping(value = { "/assess/id" }, method = RequestMethod.POST)
	public ResponseEntity<?> showUsers(@RequestParam Map<String, String> allRequestParams) {

		WebClient client = WebClient.create();
	
		
		String id = allRequestParams.get("patId");


		
		WebClient.ResponseSpec responseSpec = client.get().uri("http://localhost:8080/patient/?id="+ id).retrieve();

		String responseBody = responseSpec.bodyToMono(String.class).block();

		ObjectMapper mapper = new ObjectMapper();
		
		

		try {

			Patient patient = mapper.readValue(responseBody, new TypeReference<Patient>() {});
			
			
			
			WebClient.ResponseSpec responseSpec1 = client.get().uri("http://localhost:8082/notes/?idPatient="+ id).retrieve();

			String responseBody1 = responseSpec1.bodyToMono(String.class).block();

			ObjectMapper mapper1 = new ObjectMapper();
			
			
			List<Note> listNotes = mapper1.readValue(responseBody1, new TypeReference<List<Note>>() {
			});
			
			int age = patientServ.age(patient);
			
			
			String risque  = noteServ.risque(listNotes, age, patient);
			
			return ResponseEntity.ok(risque);

			
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		return null;

	}

}

package com.openclassrooms.mediscreenFront.controller;

import java.awt.PageAttributes.MediaType;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
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
import com.openclassromms.mediscreenFront.form.PatientForm;
import com.openclassrooms.mediscreenFront.model.Patient;

@Controller
public class PatientController {

	@GetMapping({ "/listPatient" })
	public ModelAndView showUsers() {

		WebClient client = WebClient.create();

		WebClient.ResponseSpec responseSpec = client.get().uri("http://localhost:8080/allPatients").retrieve();

		String responseBody = responseSpec.bodyToMono(String.class).block();

		ObjectMapper mapper = new ObjectMapper();

		try {

			List<Patient> listPatient = mapper.readValue(responseBody, new TypeReference<List<Patient>>() {
			});

			try {
				ModelAndView mav = new ModelAndView("list-transaction-test");

				mav.addObject("listPatient", listPatient);

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
	@GetMapping({ "/add" })
	public ModelAndView addUser() {
		ModelAndView mav = new ModelAndView("addPatient");

		PatientForm patientForm = new PatientForm();

		mav.addObject("patientForm", patientForm);

		return mav;
	}
	
	
	// API mais aul lieu de repository, requette vers une autre API
	@RequestMapping(value = { "/add" }, method = RequestMethod.POST)
	public String addPatient(Model model, @ModelAttribute("patientForm") PatientForm patientForm) {

		String family = patientForm.getFamily();
		String given = patientForm.getGiven();
		String dob = patientForm.getDob();
		String sex = patientForm.getSex();
		String address = patientForm.getAddress();
		String phone = patientForm.getPhone();

		MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

		bodyValues.add("family", family);
		bodyValues.add("given", given);
		bodyValues.add("dob", dob);
		bodyValues.add("sex", sex);
		bodyValues.add("address", address);
		bodyValues.add("phone", phone);

		WebClient client = WebClient.create();

		try {

			String response = client.post().uri(new URI("http://localhost:8080/addPatient"))
					.body(BodyInserters.fromFormData(bodyValues)).retrieve().bodyToMono(String.class).block();
			System.out.println(response);
			return "redirect:/listPatient";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "addPatient";
	}

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
	
	
	@GetMapping({ "/updatePatient" })
	public ModelAndView updatePatient(@RequestParam MultiValueMap<String, String> param) {
		ModelAndView mav = new ModelAndView("updatePatient");
		
		String family = param.getFirst("family");
		String given = param.getFirst("given");
		
		WebClient client = WebClient.create();

		WebClient.ResponseSpec responseSpec = client.get().uri("http://localhost:8080/patient/?family="+family+"&given="+given).retrieve();
		
		System.out.println("http://localhost:8080/patient/?family = "+family+"&given="+given);
		
		String responseBody = responseSpec.bodyToMono(String.class).block();

		ObjectMapper mapper = new ObjectMapper();

		PatientForm patientForm = new PatientForm();

		try {
			Patient p = mapper.readValue(responseBody, new TypeReference<Patient>() {});
			
			patientForm.setFamily(p.getFamily());
			patientForm.setGiven(p.getGiven());
			patientForm.setDob(p.getDob());
			patientForm.setSex(p.getSex());
			patientForm.setAddress(p.getAddress());
			patientForm.setPhone(p.getPhone());
			
			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		mav.addObject("patientForm", patientForm);

		return mav;
	}

	@RequestMapping(value = { "/updatePatient" }, method = RequestMethod.POST)
	public String updatePatient(Model model, @ModelAttribute("patientForm") PatientForm patientForm) {

		String family = patientForm.getFamily();
		String given = patientForm.getGiven();
		String dob = patientForm.getDob();
		String sex = patientForm.getSex();
		String address = patientForm.getAddress();
		String phone = patientForm.getPhone();

		MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

		bodyValues.add("family", family);
		bodyValues.add("given", given);
		bodyValues.add("dob", dob);
		bodyValues.add("sex", sex);
		bodyValues.add("address", address);
		bodyValues.add("phone", phone);

		WebClient client = WebClient.create();

		try {
			
			

			String response = client.post().uri(new URI("http://localhost:8080/updatePatient"))
					.body(BodyInserters.fromFormData(bodyValues)).retrieve().bodyToMono(String.class).block();
			//System.out.println("u "+response);
			return "redirect:/listPatient";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "updatePatient";
	}

}

package com.openclassrooms.mediscreenFront.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class RisqueControler {

	
	@GetMapping({ "/assess/id" })
	public ResponseEntity<?> showUsers(@RequestParam Map<String, String> allRequestParams) throws URISyntaxException {

		WebClient client = WebClient.create();
		
		String id = allRequestParams.get("patId");
		
		MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

		bodyValues.add("patId", id);
		

		
		String response = client.post().uri(new URI("http://risque:8083/assess/id"))
				.body(BodyInserters.fromFormData(bodyValues)).retrieve().bodyToMono(String.class).block();


		return ResponseEntity.ok(response);

	}
	
	@GetMapping({ "/assess/familyName" })
	public ResponseEntity<?> showFamily(@RequestParam Map<String, String> allRequestParams) throws URISyntaxException {

		WebClient client = WebClient.create();
		
		String family = allRequestParams.get("familyName");
		
		MultiValueMap<String, String> bodyValues = new LinkedMultiValueMap<>();

		bodyValues.add("familyName", family);
		

		
		String response = client.post().uri(new URI("http://risque:8083/assess/familyName"))
				.body(BodyInserters.fromFormData(bodyValues)).retrieve().bodyToMono(String.class).block();


		return ResponseEntity.ok(response);

	}
}

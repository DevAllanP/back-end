package fr.insys.commerce.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insys.commerce.dto.ContactRequest;
import fr.insys.commerce.dto.EContactRequest;
import fr.insys.commerce.dto.UtilisateurDto;
import fr.insys.commerce.service.ContactService;

@RestController
@CrossOrigin
@RequestMapping("/contact")
public record ContactController(ContactService contactService) {
	@PostMapping()
	public ResponseEntity<Void> contact(@Valid @RequestBody ContactRequest contactRequest) {
		contactService.contact(contactRequest);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/lost")
	public ResponseEntity<Void> lostPassword()  {
		contactService.lostmdp();
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/types")
	public ResponseEntity<Map<String, String>> contactTypes() {
		Map<String, String> map = new HashMap<String, String>();
		for (EContactRequest c : EContactRequest.values())
		    map.put(c.toString(), c.getContent());
		return ResponseEntity.ok().body(map);
	}
}
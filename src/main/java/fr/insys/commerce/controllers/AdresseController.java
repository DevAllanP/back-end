package fr.insys.commerce.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.insys.commerce.dto.AdresseDto;
import fr.insys.commerce.service.AdresseService;

@RestController
@CrossOrigin
@RequestMapping("/adresses")
public record AdresseController(AdresseService adresseService) {
	@GetMapping()
	public ResponseEntity<Iterable<AdresseDto>> getAllAdresses() {
		return ResponseEntity.ok().body(adresseService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AdresseDto> getAdresse(@PathVariable int id) {
		return ResponseEntity.ok().body(adresseService.getById(id));
	}

	@PostMapping("/create")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<AdresseDto> createAdresse(@RequestBody AdresseDto a) {
		return ResponseEntity.ok().body(adresseService.save(a));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Void> deleteAdresse(@PathVariable int id) {
		adresseService.delete(id);
		return ResponseEntity.ok().build();
	}
}
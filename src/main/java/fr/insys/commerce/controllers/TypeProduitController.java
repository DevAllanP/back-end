package fr.insys.commerce.controllers;

import fr.insys.commerce.dto.TypeProduitWithIdDto;
import fr.insys.commerce.dto.TypeProduitDto;

import fr.insys.commerce.service.TypeProduitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/type-produit")
public record TypeProduitController(TypeProduitService typePdtService) {
	//TODO : retourner des ResponseEntity
	@GetMapping()
	public ResponseEntity<Iterable<TypeProduitDto>> getAllTypeProduit() {
		return ResponseEntity.ok().body(typePdtService.findAll());
	}

	@GetMapping("/all-with-id")
	public ResponseEntity<Iterable<TypeProduitWithIdDto>> getAllWithId() {
		return ResponseEntity.ok().body(typePdtService.findAllWithId());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TypeProduitDto> getTypeProduitById(@PathVariable int id) {
		return ResponseEntity.ok().body(typePdtService.getById(id));
	}

	@PostMapping("/create")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<TypeProduitDto> createTypeProduit(@RequestBody TypeProduitDto typeProduitDto) {
		return ResponseEntity.ok().body(typePdtService.save(typeProduitDto));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Void> deleteTypeProduitById(@PathVariable int id) {
		typePdtService.delete(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<TypeProduitDto> updateTypeProduitById(@PathVariable int id,
			@RequestBody TypeProduitDto typeProduitDto) {
		return ResponseEntity.ok().body(typePdtService.update(id, typeProduitDto));
	}
}
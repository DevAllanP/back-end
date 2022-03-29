package fr.insys.commerce.controllers;

import fr.insys.commerce.dto.ProduitDto;
import fr.insys.commerce.dto.ProduitFiltreDto;
import fr.insys.commerce.dto.RechercheDto;
import fr.insys.commerce.service.IProduitService;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/produit")
public record ProduitController(IProduitService iProduitService) {
	//TODO : retourner des ResponseEntity
	@GetMapping()
	public ResponseEntity<Iterable<ProduitDto>> getAllProduit() {
		return ResponseEntity.ok().body(iProduitService.findAllProduit());

	}
	
	@PostMapping("/type/{type}")
	public ResponseEntity<Iterable<ProduitDto>> getProduitsByTypeAndAsc(@PathVariable String type, @RequestBody ProduitFiltreDto form) {
		return ResponseEntity.ok().body(iProduitService.findAllProduitByType(type, form));

	}

	@GetMapping("/{id}")
	public ResponseEntity<ProduitDto> getProduitById(@PathVariable int id) {
		return ResponseEntity.ok().body(iProduitService.getById(id));
	}

	@PostMapping("/create")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ProduitDto> createProduit(@RequestBody ProduitDto produitDto) {
		return ResponseEntity.ok().body(iProduitService.addProduit(produitDto));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Integer> deleteProduitById(@PathVariable int id) {
		var isRemoved = iProduitService.deleteProduitById(id);
		if (!isRemoved) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(id, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<ProduitDto> updateProduitById(@PathVariable int id, @RequestBody ProduitDto produitDto) {
		return ResponseEntity.ok().body(iProduitService.updateProduit(id, produitDto));
	}

	@DeleteMapping("/{id}/disable")
	@ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<ProduitDto> desactiveProduit(@PathVariable int id) {
		iProduitService.desactive(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/search")
	@ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Iterable<ProduitDto>> recherche(@Valid @RequestBody RechercheDto form) {
		if(!form.ordre().equals("asc") && !form.ordre().equals("desc"))
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(iProduitService.recherche(form));
	}
}
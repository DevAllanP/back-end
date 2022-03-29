package fr.insys.commerce.controllers;

import fr.insys.commerce.dto.StatusCommandeDto;
import fr.insys.commerce.dto.StatusCommandeDtoWithLabel;
import fr.insys.commerce.service.StatusCommandeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/status-commande")
public record StatusCommandeController(StatusCommandeService statusCommandeService) {
	//TODO : retourner des ResponseEntity
	@GetMapping()
	public ResponseEntity<Iterable<StatusCommandeDtoWithLabel>> getAllStatusCommande() {
		return ResponseEntity.ok().body(statusCommandeService.findAll());
	}
	@GetMapping("/all-with-id")
    public ResponseEntity<Iterable<StatusCommandeDto>> getAllWithId(){
        return ResponseEntity.ok().body(statusCommandeService.findAllWithId());
    }

	@GetMapping("/{id}")
	public ResponseEntity<StatusCommandeDtoWithLabel> getStatusCommandeById(@PathVariable int id) {
		return ResponseEntity.ok().body(statusCommandeService.getById(id));
	}

	@PostMapping("/create")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<StatusCommandeDtoWithLabel> createStatusCommande(@RequestBody StatusCommandeDtoWithLabel statusCommandeDtoWithLabel) {
		return ResponseEntity.ok().body(statusCommandeService.save(statusCommandeDtoWithLabel));
	}

	@PutMapping("/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<StatusCommandeDtoWithLabel> updateStatusCommandeById(@PathVariable int id,
			@RequestBody StatusCommandeDtoWithLabel statusCommandeDtoWithLabel) {
		return ResponseEntity.ok().body(statusCommandeService.update(id, statusCommandeDtoWithLabel));
	}
}
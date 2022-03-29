package fr.insys.commerce.controllers;

import fr.insys.commerce.dto.CommandeDto;
import fr.insys.commerce.dto.CommandeDtoWithId;
import fr.insys.commerce.service.CommandeService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/commande")
public record CommandeController(CommandeService icommandeService) {
	@ApiOperation(value="Lister les commandes", notes="findAllCommandes")
	@GetMapping()
	public ResponseEntity<Iterable<CommandeDto>> findAllCommandes() {
		return ResponseEntity.ok().body(icommandeService.findAll());
	}
	@ApiOperation(value="Lister les commandes", notes="findAllCommandes")
	@GetMapping("/findAllWithId")
	public ResponseEntity<Iterable<CommandeDtoWithId>> findAllCommandesWithId() {
		return ResponseEntity.ok().body(icommandeService.findAllWithId());
	}

	@ApiOperation(value="Retrouver une commande par son id", notes="getCommandeById")
	@GetMapping("/{id}")
	public ResponseEntity<CommandeDto> getCommandeById(@PathVariable int id) {
		return ResponseEntity.ok().body(icommandeService.findById(id));
	}

	@ApiOperation(value="Création d'une commande", notes="Seul les clients peuvent passer des commandes")
	@PostMapping("/create")//TODO : endpoint non nécéssaire pour la méthode post
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)//TODO : optionnel voir uri pour redirection
	public ResponseEntity<CommandeDto> createProduit(@RequestBody CommandeDto commandeDto) {
		return ResponseEntity.ok().body(icommandeService.save(commandeDto));
	}
	@ApiOperation(value="Update DateEnvoie", notes="")
	@PostMapping("/date-envoie/{id}/{envoie}")
	@ResponseBody
	public ResponseEntity<CommandeDto> updateDateEnvoie(@PathVariable int id, @PathVariable Timestamp envoie ) {
		/*Format : 2024-03-17 16:37:59.42*/
		return ResponseEntity.ok().body(icommandeService.updateDateEnvoie(id, envoie));
	}

	@ApiOperation(value="Update Date de Livraison", notes="")
	@PostMapping("/date-livraison/{id}/{livraison}")
	@ResponseBody
	public ResponseEntity<CommandeDto> updateDateLivraison(@PathVariable int id, @PathVariable Timestamp livraison) {
		return ResponseEntity.ok().body(icommandeService.updateDateLivraison(id,livraison ));
	}
}
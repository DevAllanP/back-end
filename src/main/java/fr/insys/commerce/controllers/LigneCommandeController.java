package fr.insys.commerce.controllers;

import fr.insys.commerce.dto.LigneCommandeDto;
import fr.insys.commerce.service.LigneCommandeService;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/ligne-commande")
public record LigneCommandeController(LigneCommandeService ligneCmdService) {
    @ApiOperation(value="Retrouver toutes les lignes de Commandes", notes="getAllLignesCommande")
    @GetMapping()
    public ResponseEntity<List<LigneCommandeDto>> getAllLignesCommande(){
        return ResponseEntity.ok().body(ligneCmdService.findAll());
    }
    //TODO : retourner une ResponseEntity
    @ApiOperation(value="Retrouver une ligne de commande par son Id", notes="getLignCmdById")
    @GetMapping("/{id}")
    public ResponseEntity<LigneCommandeDto> getLignCmdById(@PathVariable int id){
        return  ResponseEntity.ok().body(ligneCmdService.getById(id));
    }

    @ApiOperation(value="Création d'une ligne de commande", notes="createLigneCommande")
    @PostMapping("")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<LigneCommandeDto> createLigneCommande(@RequestBody LigneCommandeDto ligneCommandeDto){
        return ResponseEntity.ok(ligneCmdService.save(ligneCommandeDto));
    }
    //TODO : on ne delete pas une Ligne de Commande sinon on supprime l'user associé, on désactivera
/* @DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Void> deleteLigneCommandeById(@PathVariable int id) {
		ligneCmdService.delete(id);
		return ResponseEntity.ok().build();
	}*/

}
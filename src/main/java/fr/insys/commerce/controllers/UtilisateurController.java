package fr.insys.commerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import fr.insys.commerce.dto.LoginDto;
import fr.insys.commerce.dto.UtilisateurCoordDto;
import fr.insys.commerce.dto.UtilisateurDisableDto;
import fr.insys.commerce.dto.UtilisateurDto;
import fr.insys.commerce.dto.UtilisateurMdpDto;
import fr.insys.commerce.exceptions.NotFoundException;
import fr.insys.commerce.models.UtilisateurEntity;
import fr.insys.commerce.repository.UtilisateurRepository;
import fr.insys.commerce.service.UtilisateurService;

@RestController
@CrossOrigin
@RequestMapping("/users")
public record UtilisateurController(UtilisateurService utilisateurService ,  UtilisateurRepository utilisateurRepository ) {
	
	
	
	//TODO : retourner des ResponseEntity
	@GetMapping()
	public ResponseEntity<Iterable<UtilisateurDto>> getAllUsers(){
		return ResponseEntity.ok().body(utilisateurService.findAll());
	}
	
	@GetMapping("/id:{id}")
	public ResponseEntity<UtilisateurDto> getUser(@PathVariable Integer id) {
		return  ResponseEntity.ok().body(utilisateurService.getById(id));
	}
	
	@GetMapping("/mail:{mail}")
	public ResponseEntity<UtilisateurDto> getUserByMail(@PathVariable String mail) {
		return ResponseEntity.ok().body(utilisateurService.getByMail(mail));
	}
	
	@GetMapping("/nom:{nom}")
	public ResponseEntity<List<UtilisateurDto>> getUsersByName(@PathVariable String nom) {
		return ResponseEntity.ok().body(utilisateurService.findByNom(nom));
	}

	@PostMapping("/create")
	@ResponseBody
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UtilisateurDto> createUser(@Valid @RequestBody UtilisateurDto u) {
		return ResponseEntity.ok().body(utilisateurService.save(u));
	}
	
	@DeleteMapping("/{id}/delete")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<Void> deleteUser(@PathVariable int id) {
		utilisateurService.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<UtilisateurDto> login(@Valid @RequestBody LoginDto loginRequest) {
		return ResponseEntity.ok(utilisateurService.login(loginRequest));
	}

	
	@PutMapping("/update:{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ResponseEntity<UtilisateurDto> update(@PathVariable int id, @Valid @RequestBody UtilisateurDto u  ){
		return ResponseEntity.ok().body(utilisateurService.update(id, u));
	}
	
    @PutMapping("/update")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UtilisateurCoordDto> update(@Valid @RequestBody UtilisateurCoordDto u){
    	
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UtilisateurEntity utilisateur = utilisateurRepository.findByEmail(((UserDetails) principal).getUsername())
                .orElseThrow(() -> new NotFoundException());
        return ResponseEntity.ok().body(utilisateurService.updateCoord(utilisateur.getId(), u));
    }
    
    @PutMapping("/updateMdp")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UtilisateurMdpDto> updateMdp(@Valid @RequestBody UtilisateurMdpDto u){
    	
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UtilisateurEntity utilisateur = utilisateurRepository.findByEmail(((UserDetails) principal).getUsername())
                .orElseThrow(() -> new NotFoundException());
        return ResponseEntity.ok().body(utilisateurService.updateMdp(utilisateur.getId(), u));
    }
    
    @PutMapping("/updateDisable")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UtilisateurDisableDto> update(@Valid @RequestBody UtilisateurDisableDto u){
    	
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UtilisateurEntity utilisateur = utilisateurRepository.findByEmail(((UserDetails) principal).getUsername())
                .orElseThrow(() -> new NotFoundException());
        return ResponseEntity.ok().body(utilisateurService.updateDisable(utilisateur.getId(), u));
    }
	
}


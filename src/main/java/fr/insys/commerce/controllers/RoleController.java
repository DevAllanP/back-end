package fr.insys.commerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.insys.commerce.dto.RoleDto;
import fr.insys.commerce.service.RoleService;

@RestController
@CrossOrigin
@RequestMapping("/roles")
public record RoleController(RoleService roleService) {
	//TODO : retourner des ResponseEntity
	@GetMapping()
	public ResponseEntity<Iterable<RoleDto>> getAllRoles() {
		return ResponseEntity.ok().body(roleService.findAll());
	}
}
package fr.insys.commerce.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public record LoginDto(
	@NotBlank(message = "L'adresse e-mail doit être rempli.") @Email(message = "L'adresse e-mail est mal formatée.") String email,
	@NotBlank(message = "Le mot de passe doit être rempli.") String password
) {}


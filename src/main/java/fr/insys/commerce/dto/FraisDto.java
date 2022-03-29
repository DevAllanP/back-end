package fr.insys.commerce.dto;

import javax.validation.constraints.NotBlank;

public record FraisDto (
	@NotBlank String label,
	float montant
) {}
package fr.insys.commerce.dto;

import javax.validation.constraints.NotBlank;

public record FraisWithIdDto(
		Integer id,
		@NotBlank String label,
		Float montant
) {}
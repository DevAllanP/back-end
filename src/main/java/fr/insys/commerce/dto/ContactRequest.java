package fr.insys.commerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record ContactRequest(
	@NotNull EContactRequest type,
	@NotBlank String objet,
	@NotBlank String message
) {}
package fr.insys.commerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record AdresseDto (
		@NotNull Integer numero,
		@NotBlank String rue,
		@NotBlank String ville,
		@NotBlank String codePostal
) {}
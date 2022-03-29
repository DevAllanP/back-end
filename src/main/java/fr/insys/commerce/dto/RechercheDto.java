package fr.insys.commerce.dto;

import javax.validation.constraints.NotBlank;

public record RechercheDto(
		@NotBlank(message="La recherche ne doit pas être vide") String recherche,
		@NotBlank(message="L'ordre de tri doit être renseigné") String ordre,
		Float min,
		Float max
) {}
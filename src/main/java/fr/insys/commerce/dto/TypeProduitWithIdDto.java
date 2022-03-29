package fr.insys.commerce.dto;

import javax.validation.constraints.NotBlank;

public record TypeProduitWithIdDto(
		Integer id,
		@NotBlank(message="Un type de produit doit avoir un libellé.") String label
) {}
package fr.insys.commerce.dto;

import javax.validation.constraints.NotBlank;

public record StatusCommandeDtoWithLabel(
		@NotBlank(message="impossible d'ajouter un status de commande sans label") String label
) {}
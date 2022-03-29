package fr.insys.commerce.dto;

import javax.validation.constraints.NotBlank;

public record TagDto(
		@NotBlank(message="Un tag doit avoir un libellé.") String label
){}

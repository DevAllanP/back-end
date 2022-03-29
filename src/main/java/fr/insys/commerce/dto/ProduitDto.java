package fr.insys.commerce.dto;

import java.math.BigDecimal;
import java.util.Set;

public record ProduitDto(
		Integer idProduit,
		boolean disable,
		String nom,
		String description,
		BigDecimal prix,
		Set<TagDto> tagDtos,
		TypeProduitDto typeProduitDto
) {}
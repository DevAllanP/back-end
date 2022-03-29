package fr.insys.commerce.dto;

public record ProduitFiltreDto(
		String ordre,
		Float min,
		Float max,
		String tag
) {}
package fr.insys.commerce.dto;

import java.math.BigDecimal;

public record LigneCommandeDto(
		int quantite,
		ProduitDto produitDto,
		CommandeDto commandeDto,
		BigDecimal prixUnitaire
) {}
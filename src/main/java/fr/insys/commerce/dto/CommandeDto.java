package fr.insys.commerce.dto;

import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;
import java.util.List;

public record CommandeDto (
		@ApiModelProperty(notes="date de commande", name="dateCommande", required=false)
		Timestamp dateCommande,
		Timestamp dateEnvoie,
		Timestamp dateLivraison,
		UtilisateurSimpleDto utilisateur,
		List<FraisDto> listFrais,
		StatusCommandeDtoWithLabel statusDeCommande
){}
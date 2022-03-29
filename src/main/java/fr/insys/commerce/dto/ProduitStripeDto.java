package fr.insys.commerce.dto;

import java.math.BigDecimal;

import org.javamoney.moneta.Money;

import fr.insys.commerce.util.CurrencyUtil;

public class ProduitStripeDto {

	private String nom;
	
	private BigDecimal prix;

	private Money price;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public Money getPrice() {
		return Money.of(prix, CurrencyUtil.EUR);
	}

	public void setPrice(Money price) {
		this.price = price;
	}
	
}

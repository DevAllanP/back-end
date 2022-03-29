package fr.insys.commerce.models;

import java.util.List;

import fr.insys.commerce.dto.LigneCommandeDto;
import fr.insys.commerce.dto.LigneCommandeStripeDto;

public class Order {
	List<LigneCommandeStripeDto> lineItems;

	public List<LigneCommandeStripeDto> getLineItems() {
		return lineItems;
	}

	public void setLineItems(List<LigneCommandeStripeDto> lineItems) {
		this.lineItems = lineItems;
	}
}

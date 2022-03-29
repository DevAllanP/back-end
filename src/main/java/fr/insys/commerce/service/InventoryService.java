package fr.insys.commerce.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import fr.insys.commerce.dto.ProduitStripeDto;

public interface InventoryService {
List<ProduitStripeDto> getInventory();
	

}

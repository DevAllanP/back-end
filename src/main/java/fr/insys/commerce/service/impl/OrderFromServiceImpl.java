package fr.insys.commerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import fr.insys.commerce.dto.LigneCommandeStripeDto;
import fr.insys.commerce.dto.ProduitStripeDto;
import fr.insys.commerce.models.Order;
import fr.insys.commerce.service.OrderFromService;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class OrderFromServiceImpl implements OrderFromService {

	private final InventoryServiceImpl inventoryService;
	
	public Order getEmptyOrder() {
        Order order = new Order();
        
        List<LigneCommandeStripeDto> lineItems = new ArrayList<LigneCommandeStripeDto>();
        
        List<ProduitStripeDto> products = inventoryService.getInventory();
        products.forEach(product -> {
        	LigneCommandeStripeDto lineItem = new LigneCommandeStripeDto();
            lineItem.setProduit(product);
            lineItem.setQuantite(0);
            
            lineItems.add(lineItem);
        });
        
        order.setLineItems(lineItems);
        
        return order;
    }
	}



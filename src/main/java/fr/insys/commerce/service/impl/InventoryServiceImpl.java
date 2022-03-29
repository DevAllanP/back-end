package fr.insys.commerce.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;
import fr.insys.commerce.dto.ProduitStripeDto;
import fr.insys.commerce.repository.IProduitRepository;
import fr.insys.commerce.service.InventoryService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

	
	
	@Override
	public List<ProduitStripeDto> getInventory() {
	    List<ProduitStripeDto> inventory = new ArrayList<ProduitStripeDto>();
	    
	    ProduitStripeDto bigMonsterShirt = new ProduitStripeDto();
	    bigMonsterShirt.setNom("Tee shirt de beau gosse ");
	    bigMonsterShirt.setPrix(new BigDecimal("2202.99"));
	    
	    ProduitStripeDto superSquirtGun = new ProduitStripeDto();
	    superSquirtGun.setNom("Super Squirt Gun");
	    superSquirtGun.setPrix(new BigDecimal("33.75"));
	    
	    inventory.add(bigMonsterShirt);
	    inventory.add(superSquirtGun);
	    
	    return inventory; 
	}

}

package fr.insys.commerce.service;

import fr.insys.commerce.dto.ProduitDto;
import fr.insys.commerce.dto.ProduitFiltreDto;
import fr.insys.commerce.dto.RechercheDto;

import java.util.List;

public interface IProduitService {
    List<ProduitDto> findAllProduit();
    List<ProduitDto> findAllProduitByType(String type, ProduitFiltreDto form);
    boolean deleteProduitById(int id);
    ProduitDto addProduit (ProduitDto produitDto);
    ProduitDto updateProduit(int id, ProduitDto produitDto);
    ProduitDto getById (int id);
	void desactive(int id);
	List<ProduitDto> recherche(RechercheDto form);
}
package fr.insys.commerce.service;

import java.util.List;

import fr.insys.commerce.dto.AdresseDto;
import fr.insys.commerce.dto.UtilisateurDto;

public interface AdresseService {
	
	List<AdresseDto> findAll();
	AdresseDto save(AdresseDto u);
	AdresseDto getById(int id);
	void delete(int id);
	public AdresseDto getByUser(UtilisateurDto u);

}

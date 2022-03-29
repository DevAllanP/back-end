package fr.insys.commerce.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.insys.commerce.mapper.AdresseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import fr.insys.commerce.dto.AdresseDto;
import fr.insys.commerce.dto.UtilisateurDto;
import fr.insys.commerce.exceptions.NotFoundException;
import fr.insys.commerce.models.AdresseEntity;
import fr.insys.commerce.repository.AdresseRepository;
import fr.insys.commerce.service.AdresseService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdresseServiceImpl implements AdresseService {
	private final AdresseRepository adresseRepository;
	private final AdresseMapper adresseMapper;

	@Override
	public List<AdresseDto> findAll() {
		List<AdresseDto> adresses = new ArrayList<>();
		for(AdresseEntity a : adresseRepository.findAll()) {
			adresses.add(adresseMapper.toDto(a));
		}
		return adresses;
	}

	@Override
	public AdresseDto save(AdresseDto a) {
		//TODO : passer par le builder
		AdresseEntity ae = new AdresseEntity();
		ae.setNumero(a.numero());
		ae.setRue(a.rue());
		ae.setVille(a.ville());
		ae.setCodePostal(a.codePostal());
		adresseRepository.save(ae);
		return adresseMapper.toDto(ae);
	}

	@Override
	public AdresseDto getById(int id) {
		Optional<AdresseEntity> a = adresseRepository.findById(id);
		if (a.isEmpty())
			throw new NotFoundException();
		return adresseMapper.toDto(a.get());

	}
	
	@Override
	public AdresseDto getByUser(UtilisateurDto u) {
		//TODO : à faire chercher l'adresse en BDD
		return u.adresse();
	}

	@Override
	public void delete(int id) {
		Optional<AdresseEntity> a = adresseRepository.findById(id);
		if(a.isEmpty()) throw new NotFoundException();
		adresseRepository.delete(a.get());	
		
	}
}
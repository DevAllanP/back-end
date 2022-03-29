package fr.insys.commerce.service;

import java.util.List;

import fr.insys.commerce.dto.LoginDto;
import fr.insys.commerce.dto.UtilisateurCoordDto;
import fr.insys.commerce.dto.UtilisateurDisableDto;
import fr.insys.commerce.dto.UtilisateurDto;

import fr.insys.commerce.dto.UtilisateurMdpDto;
import fr.insys.commerce.models.ERole;


public interface UtilisateurService {
	List<UtilisateurDto> findAll();
	UtilisateurDto save(UtilisateurDto u);
	UtilisateurDto getById(int id);
	void delete(int id);
	UtilisateurDto login(LoginDto form);
	void disableUser(UtilisateurDto u);
	List<UtilisateurDto> findByNom(String nom);
	UtilisateurDto getByMail(String mail);
	UtilisateurDto update(int id , UtilisateurDto u);
	UtilisateurCoordDto updateCoord(int id , UtilisateurCoordDto u);
	UtilisateurMdpDto updateMdp(int id , UtilisateurMdpDto u);
	UtilisateurDisableDto updateDisable(int id , UtilisateurDisableDto u);
}
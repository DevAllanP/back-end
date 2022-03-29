package fr.insys.commerce.service.impl;

import java.util.List;

import java.util.Optional;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import fr.insys.commerce.dto.LoginDto;
import fr.insys.commerce.dto.UtilisateurCoordDto;
import fr.insys.commerce.dto.UtilisateurDisableDto;
import fr.insys.commerce.dto.UtilisateurDto;
import fr.insys.commerce.dto.UtilisateurMdpDto;
import fr.insys.commerce.exceptions.EmailAlreadyUsedException;
import fr.insys.commerce.exceptions.NotFoundException;
import fr.insys.commerce.exceptions.UtilisateurNotFoundException;
import fr.insys.commerce.mapper.UtilisateurMapper;
import fr.insys.commerce.mapper.AdresseMapper;

import fr.insys.commerce.models.ERole;
import fr.insys.commerce.models.RoleEntity;
import fr.insys.commerce.models.UtilisateurEntity;
import fr.insys.commerce.repository.AdresseRepository;
import fr.insys.commerce.repository.RoleRepository;
import fr.insys.commerce.repository.UtilisateurRepository;
import fr.insys.commerce.security.jwt.JwtUtils;
import fr.insys.commerce.security.services.UserDetailsImpl;
import fr.insys.commerce.service.UtilisateurService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {
	private final AuthenticationManager authenticationManager;

	private final JwtUtils jwtUtils;
	private final UtilisateurRepository utilisateurRepository;
	private final AdresseRepository adresseRepository;


	private final PasswordEncoder encoder;
	private final UtilisateurMapper usrMapper;
	private final RoleRepository roleRepo;
	private final AdresseMapper adresseMapper;

	@Override
	public List<UtilisateurDto> findAll() {
		return utilisateurRepository.findAll().stream().map(usrMapper::toDto).collect(Collectors.toList());
	}

	private UtilisateurDto doSave(UtilisateurDto u, RoleEntity role) {
		// Si un utilisateur existe déjà en BDD avec le même e-mail, on arrête tout
		//TODO : à revoir + vérif quel type utilisateur on souhaite record
		if (utilisateurRepository.existsByEmail(u.email()))
			throw new EmailAlreadyUsedException();
		UtilisateurEntity ue = new UtilisateurEntity();
		ue.setPrenom(u.prenom());
		ue.setNom(u.nom());
		ue.setEmail(u.email());
		ue.setDisable(false);
		ue.setPassword(encoder.encode(u.password()));
		ue.setDateNaissance(u.dateNaissance());

		// Si un rôle est présent en paramètre, on le récupère
		if (role != null) {

			ue.setRole(role);
		} else if (u.roleId() != null) {
			RoleEntity roleFromDto = roleRepo.findById(u.roleId()).orElseThrow(NotFoundException::new);
			ue.setRole(roleFromDto);
		} else if (u.role() != null) {
			RoleEntity roleFromDto = roleRepo.findById(u.role().id()).orElseThrow(NotFoundException::new);
			ue.setRole(roleFromDto);
		}
		utilisateurRepository.save(ue);
		return usrMapper.toDto(ue);
	}
	//TODO : revoir les save = un unique save - on n'enregistre jamais l'user sans role
	@Override
	public UtilisateurDto save(UtilisateurDto u) {
		return doSave(u, null);
	}



	@Override
	public UtilisateurDto getById(int id) {
		UtilisateurEntity u = utilisateurRepository.findById(id).orElseThrow(NotFoundException::new);
		return usrMapper.toDto(u);
	}

	@Override
	public void delete(int id) {
		UtilisateurEntity u = utilisateurRepository.findById(id).orElseThrow(NotFoundException::new);
		if (u.getAdresseEntity() != null)
			adresseRepository.delete(u.getAdresseEntity());
		utilisateurRepository.delete(u);
	}

	@Override
	public UtilisateurDto login(LoginDto form) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(form.email(), form.password()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		UtilisateurEntity user = utilisateurRepository.findById(userDetails.getId())
				.orElseThrow(NotFoundException::new);
		return usrMapper.toDto(user, jwt);
	}

	@Override
	public void disableUser(UtilisateurDto u) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<UtilisateurDto> findByNom(String nom) {
		return utilisateurRepository.findByNom(nom).stream().map(usrMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public UtilisateurDto getByMail(String mail) {
		UtilisateurEntity u = utilisateurRepository.findByEmail(mail).orElseThrow(NotFoundException::new);
		return usrMapper.toDto(u);
	}


	@Override
	public UtilisateurDto update(int id, UtilisateurDto u) {
		UtilisateurEntity utilisateurOptional = utilisateurRepository.findById(id)
				.orElseThrow(NotFoundException::new);
		utilisateurOptional.setNom(u.nom());
		utilisateurOptional.setPrenom(u.prenom());
		utilisateurOptional.setEmail(u.email());//TODO : avant de SetEmail - vérifier s'il n'existe pas déja - retourne EmailExistException
		utilisateurOptional.setPassword(encoder.encode(u.password()));
		utilisateurOptional.setDateNaissance(u.dateNaissance());
		utilisateurOptional.setDisable(u.disable());

		utilisateurOptional
				.setAdresseEntity(adresseMapper.toEntity(u.adresse(), utilisateurOptional.getAdresseEntity().getId()));
		utilisateurRepository.save(utilisateurOptional);
		return usrMapper.toDto(utilisateurOptional);
	}
	
	@Override
	public UtilisateurCoordDto updateCoord(int id, UtilisateurCoordDto u) {
		UtilisateurEntity utilisateurOptional = utilisateurRepository.findById(id)
				.orElseThrow(NotFoundException::new);
		utilisateurOptional.setEmail(u.email());//TODO : avant de SetEmail - vérifier s'il n'existe pas déja - retourne EmailExistException
		utilisateurOptional
				.setAdresseEntity(adresseMapper.toEntity(u.adresse(), utilisateurOptional.getAdresseEntity().getId()));
		utilisateurRepository.save(utilisateurOptional);
		return usrMapper.toDtoCoord(utilisateurOptional);
	}
	
	@Override
	public UtilisateurMdpDto updateMdp(int id, UtilisateurMdpDto u) {
		
		UtilisateurEntity utilisateurOptional = utilisateurRepository.findById(id)
				.orElseThrow(NotFoundException::new);
		// encoder.matches permet de faire une verification entre ce que l'utilisateur rentre comme mdp et celle qui est encoder en BDD
		if (encoder.matches(u.oldPassword(), utilisateurOptional.getPassword())) {
			utilisateurOptional.setPassword(encoder.encode(u.password()));	
		}else {
			

			throw new UtilisateurNotFoundException("erreur");
		}
		utilisateurRepository.save(utilisateurOptional);
		return usrMapper.toDtoMdp(utilisateurOptional);
	}

	@Override
	public UtilisateurDisableDto updateDisable(int id, UtilisateurDisableDto u) {
		UtilisateurEntity utilisateurOptional = utilisateurRepository.findById(id)
				.orElseThrow(NotFoundException::new);
		if (encoder.matches(u.password(), utilisateurOptional.getPassword())) {
			utilisateurOptional.setDisable(u.disable());
			log.debug(u.disable() + "");
		}else {
			throw new UtilisateurNotFoundException("erreur");
		}
		utilisateurRepository.save(utilisateurOptional);
		return usrMapper.toDtoDisable(utilisateurOptional);
	}


	
	



		
	
}
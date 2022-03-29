package fr.insys.commerce.mapper;

import fr.insys.commerce.dto.AdresseDto;
import fr.insys.commerce.dto.UtilisateurCoordDto;
import fr.insys.commerce.dto.UtilisateurDisableDto;
import fr.insys.commerce.dto.UtilisateurDto;
import fr.insys.commerce.dto.UtilisateurMdpDto;
import fr.insys.commerce.dto.UtilisateurSimpleDto;
import fr.insys.commerce.exceptions.NotFoundException;
import fr.insys.commerce.models.AdresseEntity;
import fr.insys.commerce.models.ERole;
import fr.insys.commerce.models.RoleEntity;
import fr.insys.commerce.models.UtilisateurEntity;
import fr.insys.commerce.repository.RoleRepository;
import lombok.RequiredArgsConstructor;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UtilisateurMapper {
	private final AdresseMapper addresseMapper;
	private final RoleMapper roleMapper;
	private final RoleRepository roleRepo;
	private final PasswordEncoder encoder;

	public UtilisateurEntity toEntity(UtilisateurDto utilisateurDto) {

		RoleEntity role = roleRepo.findById(utilisateurDto.role().id()).orElseThrow(NotFoundException::new);
		return UtilisateurEntity.builder().nom(utilisateurDto.nom()).prenom(utilisateurDto.prenom())
				.email(utilisateurDto.email()).password(utilisateurDto.password())
				.dateNaissance(utilisateurDto.dateNaissance()).disable(utilisateurDto.disable()).role(role)
				.adresseEntity(addresseMapper.toEntity(utilisateurDto.adresse())).build();

		

	}

	public UtilisateurEntity toEntitySimple(UtilisateurSimpleDto utilisateurSimpleDto) {
		return UtilisateurEntity.builder().nom(utilisateurSimpleDto.nom()).prenom(utilisateurSimpleDto.prenom())
				.email(utilisateurSimpleDto.email()).password(utilisateurSimpleDto.password())
				.dateNaissance(utilisateurSimpleDto.dateNaissance()).disable(utilisateurSimpleDto.disable())
				.role(roleMapper.toEntity(utilisateurSimpleDto.role()))
				.adresseEntity(addresseMapper.toEntity(utilisateurSimpleDto.adresseDto())).build();

	}

	public UtilisateurDto toDto(UtilisateurEntity utilisateurEntity, String accessToken) {
		AdresseDto adresseDto = utilisateurEntity.getAdresseEntity() == null ? null
				: addresseMapper.toDto(utilisateurEntity.getAdresseEntity());
		return new UtilisateurDto(utilisateurEntity.getNom(), utilisateurEntity.getPrenom(),
				utilisateurEntity.getEmail(), utilisateurEntity.getPassword(), utilisateurEntity.getDateNaissance(),
				utilisateurEntity.getDisable(), roleMapper.toDto(utilisateurEntity.getRole()),
				utilisateurEntity.getRole().getId(), adresseDto, accessToken, utilisateurEntity.getRole().getLabel());
	}

	public UtilisateurDto toDto(UtilisateurEntity utilisateurEntity) {
		AdresseDto adresseDto = utilisateurEntity.getAdresseEntity() == null ? null
				: addresseMapper.toDto(utilisateurEntity.getAdresseEntity());
		return new UtilisateurDto(utilisateurEntity.getNom(), utilisateurEntity.getPrenom(),
				utilisateurEntity.getEmail(), utilisateurEntity.getPassword(), utilisateurEntity.getDateNaissance(),
				utilisateurEntity.getDisable(), roleMapper.toDto(utilisateurEntity.getRole()),
				utilisateurEntity.getRole().getId(), adresseDto, null, utilisateurEntity.getRole().getLabel());
	}

	public UtilisateurCoordDto toDtoCoord(UtilisateurEntity utilisateurEntity) {
		AdresseDto adresseDto = utilisateurEntity.getAdresseEntity() == null ? null
				: addresseMapper.toDto(utilisateurEntity.getAdresseEntity());
		return new UtilisateurCoordDto(

				utilisateurEntity.getEmail(), adresseDto);

	}

	public UtilisateurMdpDto toDtoMdp(UtilisateurEntity utilisateurEntity) {

		return new UtilisateurMdpDto(

				utilisateurEntity.getPassword(), utilisateurEntity.getPassword());

	}

	public UtilisateurDisableDto toDtoDisable(UtilisateurEntity utilisateurEntity) {

		return new UtilisateurDisableDto(

				utilisateurEntity.getDisable(),utilisateurEntity.getPassword());

	}
	
	public UtilisateurDto toDtoCommande(UtilisateurEntity utilisateurEntity, int id) {
		return new UtilisateurDto(utilisateurEntity.getNom(), utilisateurEntity.getPrenom(),
				utilisateurEntity.getEmail(), utilisateurEntity.getPassword(), utilisateurEntity.getDateNaissance(),
				utilisateurEntity.getDisable(), roleMapper.toDto(utilisateurEntity.getRole()),
				utilisateurEntity.getRole().getId(), addresseMapper.toDto(utilisateurEntity.getAdresseEntity()), null,
				utilisateurEntity.getRole().getLabel());

	}


	public UtilisateurSimpleDto toDtoSimple(UtilisateurEntity utilisateurEntity) {
		return new UtilisateurSimpleDto(utilisateurEntity.getNom(), utilisateurEntity.getPrenom(),
				utilisateurEntity.getEmail(), utilisateurEntity.getPassword(), utilisateurEntity.getDateNaissance(),
				utilisateurEntity.getDisable(), roleMapper.toDto(utilisateurEntity.getRole()),
				addresseMapper.toDto(utilisateurEntity.getAdresseEntity()));
	}





    


	public UtilisateurEntity toEntitySimple(UtilisateurSimpleDto utilisateurDto, int id) {

		return UtilisateurEntity.builder().id(id).nom(utilisateurDto.nom()).prenom(utilisateurDto.prenom())
				.email(utilisateurDto.email()).password(utilisateurDto.password())
				.dateNaissance(utilisateurDto.dateNaissance()).disable(utilisateurDto.disable())
				.role(roleMapper.toEntity(utilisateurDto.role()))
				.adresseEntity(addresseMapper.toEntity(utilisateurDto.adresseDto())).build();

	}
}

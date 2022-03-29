package fr.insys.commerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.insys.commerce.dto.UtilisateurCoordDto;
import fr.insys.commerce.models.UtilisateurEntity;

@Repository
public interface UtilisateurRepository extends JpaRepository<UtilisateurEntity, Integer> {
	Optional<UtilisateurEntity> findByEmail(String email);
	Optional<UtilisateurEntity> findByEmail(UtilisateurCoordDto utilisateurCoordDto);
	List<UtilisateurEntity> findByNom(String nom);
	boolean existsByEmail(String email);
}

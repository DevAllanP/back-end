package fr.insys.commerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.insys.commerce.models.ERole;
import fr.insys.commerce.models.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
	Optional<RoleEntity> findByLabel(ERole roleClient);
}

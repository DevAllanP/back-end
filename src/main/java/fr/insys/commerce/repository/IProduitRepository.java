package fr.insys.commerce.repository;

import fr.insys.commerce.models.ProduitEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProduitRepository extends JpaRepository<ProduitEntity, Integer>, JpaSpecificationExecutor<ProduitEntity> {
    Optional<ProduitEntity> findByNom(String nom);
}
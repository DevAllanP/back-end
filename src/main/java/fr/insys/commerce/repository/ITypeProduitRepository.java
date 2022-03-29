package fr.insys.commerce.repository;



import fr.insys.commerce.models.TypeProduitEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITypeProduitRepository extends JpaRepository<TypeProduitEntity, Integer> {
    Optional<TypeProduitEntity> findByLabel(String label);
}

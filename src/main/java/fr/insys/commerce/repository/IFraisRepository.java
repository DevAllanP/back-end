package fr.insys.commerce.repository;

import fr.insys.commerce.models.FraisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IFraisRepository extends JpaRepository<FraisEntity, Integer> {
    boolean existsByLabel(String label);
    Optional<FraisEntity> findByLabel(String label);
}

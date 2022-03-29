package fr.insys.commerce.repository;

import fr.insys.commerce.models.StatusCommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusCommandeRepository extends JpaRepository<StatusCommandeEntity, Integer> {
}

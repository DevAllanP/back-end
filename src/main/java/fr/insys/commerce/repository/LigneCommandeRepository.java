package fr.insys.commerce.repository;

import fr.insys.commerce.models.LigneCommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommandeEntity, Integer> {
}

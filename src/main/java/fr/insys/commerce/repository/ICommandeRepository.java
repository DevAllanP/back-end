package fr.insys.commerce.repository;

import fr.insys.commerce.models.CommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommandeRepository extends JpaRepository<CommandeEntity, Integer> {
}

package fr.insys.commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.insys.commerce.models.AdresseEntity;

@Repository
public interface AdresseRepository extends JpaRepository<AdresseEntity, Integer> {

}

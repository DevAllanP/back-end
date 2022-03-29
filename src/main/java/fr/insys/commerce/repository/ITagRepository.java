package fr.insys.commerce.repository;

import fr.insys.commerce.models.TagEntity;
import fr.insys.commerce.models.TypeProduitEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITagRepository extends JpaRepository<TagEntity, Integer> {
	@Query("SELECT DISTINCT t FROM TagEntity t JOIN t.listProduit p WHERE p.typeProduitEntity = ?1")
	List<TagEntity> findAllByTypeProduit(TypeProduitEntity tp);
	Optional<TagEntity> findByLabel(String label);
}
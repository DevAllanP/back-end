package fr.insys.commerce.service.impl;

import fr.insys.commerce.dto.ProduitDto;
import fr.insys.commerce.dto.ProduitFiltreDto;
import fr.insys.commerce.dto.RechercheDto;
import fr.insys.commerce.exceptions.NotFoundException;
import fr.insys.commerce.mapper.ProduitMapper;
import fr.insys.commerce.mapper.TagMapper;
import fr.insys.commerce.models.ProduitEntity;
import fr.insys.commerce.models.TagEntity;
import fr.insys.commerce.models.TypeProduitEntity;
import fr.insys.commerce.repository.IProduitRepository;
import fr.insys.commerce.repository.ITagRepository;
import fr.insys.commerce.repository.ITypeProduitRepository;
import fr.insys.commerce.search.ERequeteOperateur;
import fr.insys.commerce.search.ProduitSpecification;
import fr.insys.commerce.service.IProduitService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProduitServiceImpl implements IProduitService {
	private final IProduitRepository iProduitRepository;
	private final ITypeProduitRepository iTypeProduitRepository;

	private final ProduitMapper produitMapper;
	private final TagMapper tagMapper;
	private final ITagRepository tagRepo;


	@Override
	public List<ProduitDto> findAllProduit() {
        return iProduitRepository.findAll().stream()
                .map(p -> produitMapper.toDto(p))
                .collect(Collectors.toList());
	}


	@Override
	public List<ProduitDto> findAllProduitByType(String type, ProduitFiltreDto form) {
		TypeProduitEntity tp = iTypeProduitRepository.findByLabel(type).orElseThrow(NotFoundException::new);
		if(!form.ordre().equals("asc") && !form.ordre().equals("desc"))
			throw new NotFoundException();
		Sort sort = Sort.by(form.ordre().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "prix");
		Specification<ProduitEntity> req = new ProduitSpecification("disable", ERequeteOperateur.EGALE, false);
		req = Specification.where(req).and(new ProduitSpecification("typeProduitEntity", ERequeteOperateur.EGALE, tp));
		if(form.tag() != null && !form.tag().isBlank()) {
			TagEntity tag = tagRepo.findByLabel(form.tag()).orElseThrow(NotFoundException::new);
			req = Specification.where(req).and(new ProduitSpecification("listTag", ERequeteOperateur.IS_TAG, tag));
		}
		if(form.min() != null)
			req = Specification.where(req).and(new ProduitSpecification("prix", ERequeteOperateur.SUP_EGALE, form.min()));
		if(form.max() != null)
			req = Specification.where(req).and(new ProduitSpecification("prix", ERequeteOperateur.INF_EGALE, form.max()));
        return iProduitRepository.findAll(req, sort).stream()
                .map(produitMapper::toDto)
                .toList();
	}

	@Override
	public ProduitDto getById(int id) {
		ProduitEntity produitDao = iProduitRepository.findById(id).orElseThrow(() -> new NotFoundException());
		return produitMapper.toDto(produitDao);
	}

	@Transactional
	@Override
	public ProduitDto addProduit(ProduitDto produitDto) {
		String labelle = produitDto.typeProduitDto().label();
		Optional<TypeProduitEntity> pi = iTypeProduitRepository.findByLabel(labelle);
		ProduitEntity savedProduit = new ProduitEntity();
		ProduitEntity produit = new ProduitEntity();
		if (pi.isEmpty()) {
			TypeProduitEntity typeProduitEntity = new TypeProduitEntity();
			typeProduitEntity.setLabel(labelle);
			iTypeProduitRepository.save(typeProduitEntity);
			produit = produitMapper.toEntity(produitDto);
			produit.setTypeProduitEntity(typeProduitEntity);
			savedProduit = iProduitRepository.save(produit);
		} else if (pi.isPresent()) {
			produit = produitMapper.toEntity(produitDto);
			produit.setTypeProduitEntity(pi.get());
			savedProduit = iProduitRepository.save(produit);
		} else {
			throw new NotFoundException();
		}
		return produitMapper.toDto(savedProduit);
	}

	@Override
	public ProduitDto updateProduit(int id, ProduitDto produitDto) {
		ProduitEntity produitEntity = iProduitRepository.findById(id).orElseThrow(NotFoundException::new);
		TypeProduitEntity type = iTypeProduitRepository.findByLabel(produitDto.typeProduitDto().label()).orElseThrow(NotFoundException::new);
		produitEntity.setNom(produitDto.nom());
		produitEntity.setDisable(produitDto.disable());
		produitEntity.setDescription(produitDto.description());
		produitEntity.setPrix(produitDto.prix());
		produitEntity.setListTag(produitDto.tagDtos().stream().map(tagMapper::toEntity).collect(Collectors.toSet()));
		produitEntity.setTypeProduitEntity(type);
		iProduitRepository.save(produitEntity);
		return produitMapper.toDto(produitEntity);
	}

	@Override
	//TODO:remplacer la signature par un void
	public boolean deleteProduitById(int id) {
		var isRemoved = false;
		Optional<ProduitEntity> produitDao = iProduitRepository.findById(id);
		if(produitDao.isPresent()) {
			iProduitRepository.deleteById(produitDao.get().getId());
			isRemoved = true;
		}
		return isRemoved;
	}
	
	@Override
	public void desactive(int id) {
		ProduitEntity produit = iProduitRepository.findById(id).orElseThrow(NotFoundException::new);
		produit.setDisable(true);
		iProduitRepository.save(produit);
	}
	
	private Specification<ProduitEntity> rechercheSpec(String mot) {
		return (new ProduitSpecification("nom", ERequeteOperateur.COMME, mot)).or(new ProduitSpecification("description", ERequeteOperateur.COMME, mot));
	}

	@Override
	public List<ProduitDto> recherche(RechercheDto form) {
		Sort sort = Sort.by(form.ordre().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, "prix");
		Set<String> mots = Set.of(form.recherche().split(" "));
		List<Specification<ProduitEntity>> specs = mots.stream().map(this::rechercheSpec).toList();
		Specification<ProduitEntity> req = new ProduitSpecification("disable", ERequeteOperateur.EGALE, false);
        for (int i = 0; i < specs.size(); i++)
            req = Specification.where(req).and(specs.get(i));
        if(form.min() != null)
        	req = Specification.where(req).and(new ProduitSpecification("prix", ERequeteOperateur.SUP_EGALE, form.min()));
        if(form.max() != null)
        	req = Specification.where(req).and(new ProduitSpecification("prix", ERequeteOperateur.INF_EGALE, form.max()));
		return iProduitRepository.findAll(req, sort).stream().map(produitMapper::toDto).toList();
	}
}
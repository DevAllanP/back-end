package fr.insys.commerce.service.impl;

import fr.insys.commerce.dto.TypeProduitWithIdDto;
import fr.insys.commerce.dto.TypeProduitDto;
import fr.insys.commerce.exceptions.NotFoundException;
import fr.insys.commerce.mapper.TypeProduitMapper;
import fr.insys.commerce.models.TypeProduitEntity;
import fr.insys.commerce.repository.ITypeProduitRepository;
import fr.insys.commerce.service.TypeProduitService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TypeProduitServiceImpl implements TypeProduitService {
	private final ITypeProduitRepository tpRepo;
	private final TypeProduitMapper tpMapper;;

	@Override
	public List<TypeProduitDto> findAll() {
		return tpRepo.findAll().stream().map(tpMapper::toDto).collect(Collectors.toList());
	}

	@Override
	public TypeProduitDto save(TypeProduitDto tpDto) {
		TypeProduitEntity tp = tpMapper.toEntity(tpDto);
		tpRepo.save(tp);
		return tpMapper.toDto(tp);
	}

	@Override
	public TypeProduitDto getById(int id) {
		TypeProduitEntity tp = tpRepo.findById(id).orElseThrow(NotFoundException::new);
		return tpMapper.toDto(tp);
	}

	@Override
	public void delete(int id) {
		tpRepo.findById(id).orElseThrow(NotFoundException::new);
		tpRepo.deleteById(id);
	}

	@Override
	public TypeProduitDto update(int id, TypeProduitDto tpDto) {
		TypeProduitEntity tp = tpRepo.findById(id).orElseThrow(NotFoundException::new);
		tp.setLabel(tpDto.label());
		tpRepo.save(tp);
		return tpMapper.toDto(tp);
	}

	@Override
	public List<TypeProduitWithIdDto> findAllWithId() {
		return tpRepo.findAll().stream().map(tpMapper::toDtoWithId).collect(Collectors.toList());
	}
}

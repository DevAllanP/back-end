package fr.insys.commerce.service;

import fr.insys.commerce.dto.TypeProduitWithIdDto;
import fr.insys.commerce.dto.TypeProduitDto;

import java.util.List;

public interface TypeProduitService {
    List<TypeProduitDto> findAll();
    List<TypeProduitWithIdDto> findAllWithId();
    TypeProduitDto save(TypeProduitDto typeProduit);
    TypeProduitDto getById(int id);
    void delete (int id);
    TypeProduitDto update(int id, TypeProduitDto typeProduitDto);
}

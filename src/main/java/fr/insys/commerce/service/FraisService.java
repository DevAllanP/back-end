package fr.insys.commerce.service;

import fr.insys.commerce.dto.FraisDto;
import fr.insys.commerce.dto.FraisWithIdDto;

import java.util.InvalidPropertiesFormatException;
import java.util.List;

public interface FraisService {
    List<FraisDto> findAll();
    List<FraisWithIdDto> findAllWithId();
    FraisDto save(FraisDto fraisDto);
    FraisDto getById(int id);
    void delete (int id);
    FraisDto update (int id, FraisDto fraisDto);
}

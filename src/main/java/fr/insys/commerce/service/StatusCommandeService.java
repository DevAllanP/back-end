package fr.insys.commerce.service;

import fr.insys.commerce.dto.StatusCommandeDto;
import fr.insys.commerce.dto.StatusCommandeDtoWithLabel;

import java.util.List;

public interface StatusCommandeService {
    List<StatusCommandeDtoWithLabel> findAll();
    List<StatusCommandeDto> findAllWithId();
    StatusCommandeDtoWithLabel save(StatusCommandeDtoWithLabel statusCommandeDto);
    StatusCommandeDtoWithLabel getById(int id);
    void delete (int id);
    StatusCommandeDtoWithLabel update (int id, StatusCommandeDtoWithLabel statusCommandeDto);

}

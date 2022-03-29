package fr.insys.commerce.service;

import fr.insys.commerce.dto.LigneCommandeDto;

import java.util.List;

public interface LigneCommandeService {
    List<LigneCommandeDto> findAll();
    LigneCommandeDto save(LigneCommandeDto ligneCommandeQteDto);
    LigneCommandeDto getById(int id);
    void delete (int id);
    LigneCommandeDto update(int id, LigneCommandeDto lCmdQte);
}

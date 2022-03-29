package fr.insys.commerce.service;

import fr.insys.commerce.dto.CommandeDto;
import fr.insys.commerce.dto.CommandeDtoWithId;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface CommandeService {
    List<CommandeDto> findAll();
    List<CommandeDtoWithId> findAllWithId();
    CommandeDto findById(int id);
    CommandeDto save(CommandeDto commandeDto);
    CommandeDto cancel(CommandeDto commandeDto);
    CommandeDto updateDateEnvoie(int id, Timestamp dateEnvoie);
    CommandeDto updateDateLivraison(int id, Timestamp dateEdateLivraison);

}

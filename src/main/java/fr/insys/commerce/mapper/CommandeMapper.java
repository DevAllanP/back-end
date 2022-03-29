package fr.insys.commerce.mapper;

import fr.insys.commerce.dto.CommandeDto;
import fr.insys.commerce.dto.CommandeDtoWithId;
import fr.insys.commerce.models.CommandeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
@RequiredArgsConstructor
public class CommandeMapper {
    private final UtilisateurMapper utilisateurmapper;
    private final FraisMapper fraisMapper;
    private final StatusCommandeMapper statusCommandeMapper;


    public CommandeEntity toEntity(CommandeDto commandeDto){
        return CommandeEntity.builder()
                .dateCommande(commandeDto.dateCommande())
                .dateEnvoie(commandeDto.dateEnvoie())
                .dateLivraison(commandeDto.dateLivraison())
                .listFrais(commandeDto.listFrais().stream().map(fraisMapper::toEntity).collect(Collectors.toList()))
                .statusDeCommande(statusCommandeMapper.toEntity(commandeDto.statusDeCommande()))
                .build();
    }
    public CommandeDto toDto(CommandeEntity commandeEntity){
        return new CommandeDto(
                commandeEntity.getDateCommande(),
                commandeEntity.getDateEnvoie(),
                commandeEntity.getDateLivraison(),
                utilisateurmapper.toDtoSimple(commandeEntity.getUser()),
                commandeEntity.getListFrais().stream().map(fraisMapper::toDto).collect(Collectors.toList()),
                statusCommandeMapper.toDto(commandeEntity.getStatusDeCommande())

        );
    }
    public CommandeDtoWithId toDtoWithId(CommandeEntity commandeEntity){
        return new CommandeDtoWithId(
                commandeEntity.id,
                commandeEntity.getDateCommande(),
                commandeEntity.getDateEnvoie(),
                commandeEntity.getDateLivraison(),
                utilisateurmapper.toDtoSimple(commandeEntity.getUser()),
                commandeEntity.getListFrais().stream().map(fraisMapper::toDto).collect(Collectors.toList()),
                statusCommandeMapper.toDto(commandeEntity.getStatusDeCommande())

        );
    }
}
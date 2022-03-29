package fr.insys.commerce.service.impl;

import fr.insys.commerce.dto.CommandeDto;
import fr.insys.commerce.dto.CommandeDtoWithId;
import fr.insys.commerce.dto.FraisDto;
import fr.insys.commerce.dto.FraisWithIdDto;
import fr.insys.commerce.exceptions.NotFoundException;
import fr.insys.commerce.exceptions.UtilisateurNotFoundException;
import fr.insys.commerce.mapper.*;
import fr.insys.commerce.models.*;
import fr.insys.commerce.repository.ICommandeRepository;
import fr.insys.commerce.repository.IFraisRepository;
import fr.insys.commerce.repository.IStatusCommandeRepository;
import fr.insys.commerce.repository.UtilisateurRepository;
import fr.insys.commerce.service.CommandeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final ICommandeRepository iCommandeRepository;
    private final UtilisateurRepository utilisateurRepository;
    private final IStatusCommandeRepository iStatusCommandeRepository;
    private final IFraisRepository iFraisRepository;
    private final CommandeMapper commandeMapper;
    private final FraisMapper fraisMapper;




    @Override
    public List<CommandeDto> findAll() {
        return iCommandeRepository.findAll().stream().map(commandeMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public List<CommandeDtoWithId> findAllWithId() {
        return iCommandeRepository.findAll().stream().map(commandeMapper::toDtoWithId).collect(Collectors.toList());
    }

    @Override
    public CommandeDto findById(int id) {
        Optional<CommandeEntity> commandeEntity = iCommandeRepository.findById(id);
        return commandeEntity.map(commandeMapper::toDto).orElseThrow(NotFoundException::new);
    }

    @Override
    public CommandeDto save(CommandeDto commandeDto) {

        /*Modele pour Swagger :
            {
                "listFrais": [
                    {"label": "frais divers","montant": 45.25},
                    {"label": "frais annexes","montant": 65.25}
                  ]
            }

        *
        * */

        //TODO : Règles métier : Quand on passe la commande attribuée un liste de frais en fonction du produit(informations produit retrouvé par la ligne de commande)
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UtilisateurEntity utilisateur = utilisateurRepository.findByEmail(((UserDetails) principal).getUsername())
                .orElseThrow(() -> new NotFoundException());


        List<StatusCommandeEntity> listStatus = iStatusCommandeRepository.findAll();
        List<FraisEntity> fraisEntities = new ArrayList<>();
        List<FraisDto> fraisDtos = commandeDto.listFrais();
        fraisDtos.stream().forEach(f->{
            Optional<FraisEntity> fraisEntity = iFraisRepository.findByLabel(f.label());
            if(fraisEntity.isPresent()){
                fraisEntities.add(fraisEntity.get());
            }else{
                fraisEntities.add(fraisMapper.toEntity(f));
            }});



        CommandeEntity commandeEntity = CommandeEntity.builder()
                .dateCommande(Timestamp.from(Instant.now()))
                .dateEnvoie(Timestamp.valueOf("2022-01-01 00:00:00"))
                .dateLivraison(Timestamp.valueOf("2022-01-01 00:00:00"))
                .statusDeCommande(listStatus.get(0))
                .listFrais(fraisEntities)
                .user(utilisateur)
                .build();

        iCommandeRepository.save(commandeEntity);
        return  commandeMapper.toDto(commandeEntity);

    }

    @Override
    public CommandeDto cancel(CommandeDto commandeDto) {
        //TODO : set status commande to annulé
        return null;
    }

    @Override
    public CommandeDto updateDateEnvoie(int id, Timestamp dateEnvoie) {
        CommandeEntity commande = iCommandeRepository.findById(id).orElseThrow(NotFoundException::new);
        commande.setDateEnvoie(dateEnvoie);
        return commandeMapper.toDto(iCommandeRepository.save(commande));
    }

    @Override
    public CommandeDto updateDateLivraison(int id, Timestamp dateLivraison) {
        CommandeEntity commande = iCommandeRepository.findById(id).orElseThrow(NotFoundException::new);
        commande.setDateLivraison(dateLivraison);
        return commandeMapper.toDto(iCommandeRepository.save(commande));
    }


}

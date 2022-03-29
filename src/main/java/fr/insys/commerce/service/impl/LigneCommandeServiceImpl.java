package fr.insys.commerce.service.impl;


import fr.insys.commerce.dto.FraisDto;
import fr.insys.commerce.dto.LigneCommandeDto;
import fr.insys.commerce.exceptions.NotFoundException;
import fr.insys.commerce.exceptions.NotFoundExceptionWithMsg;
import fr.insys.commerce.mapper.*;
import fr.insys.commerce.models.*;
import fr.insys.commerce.repository.*;
import fr.insys.commerce.service.LigneCommandeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class LigneCommandeServiceImpl implements LigneCommandeService {
    //TODO : A refaire apres MAJ Commande Entity
    private final LigneCommandeRepository iLigneCmdRepo;
    private final UtilisateurRepository utilisateurRepository;
    private final IStatusCommandeRepository iStatusCommandeRepository;
    private final IFraisRepository iFraisRepository;
    private final IProduitRepository iProduitRepository;

    private final CommandeMapper commandeMapper;
    private final ProduitMapper produitMapper;
    private final LignCommandeMapper lignCommandeMapper;


    @Override
    public List<LigneCommandeDto> findAll() {
        return iLigneCmdRepo.findAll().stream().map(lignCommandeMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public LigneCommandeDto getById(int id) {
        LigneCommandeEntity ligneCmd = iLigneCmdRepo.findById(id).orElseThrow(NotFoundException::new);
        return new LigneCommandeDto(
                ligneCmd.getQuantite(),
                produitMapper.toDto(ligneCmd.getProduits()),
                commandeMapper.toDto(ligneCmd.getCommandeEntity()),
                ligneCmd.getPrixUnitaire()
        );
    }

    @Override
    public LigneCommandeDto save(LigneCommandeDto ligneCommandeDto) {
        //TODO : Rechercher l'utilisateur en cours par le context et non plus par l'email
        /*Modele pour Swagger  :
{              "commandeDto": {
                "listFrais": [
                  {"label": "frais de dossier","montant": 10},
                  {"label": "frais de livraison","montant": 5}
                ]},

              "produitDto": {"idProduit": 1},
              "quantite": 2
}
        * */

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UtilisateurEntity utilisateur = utilisateurRepository.findByEmail(principal.getUsername())
                .orElseThrow(() -> new NotFoundException());

        List<StatusCommandeEntity> statusCommande =iStatusCommandeRepository.findAll();

        //Regle : La liste de frais ne doit pas contenir de labels dupliqués
        List<FraisEntity> newFraisLabel = new ArrayList<>();
        List<FraisEntity> existingFraisLabel = new ArrayList<>();
        for(FraisDto frais : ligneCommandeDto.commandeDto().listFrais()){
            Optional<FraisEntity> listFrais = iFraisRepository.findByLabel(frais.label());
            if(listFrais.isEmpty()){
                newFraisLabel.add(FraisEntity.builder()
                        .label(frais.label())
                        .montant(frais.montant())
                        .build());
            }else{
                existingFraisLabel.add(listFrais.get());
            }

        }
        newFraisLabel.addAll(existingFraisLabel);
        //-----------------------------------------------------------------------------------

        /*

         * On crée un nouvelle CommandeEntity qui passera en param de LignCommandeEntity
         * On crée une date de commande sur Instant.now
         * on set arbitrairement une date d'envoie et de livraison, ces info devront être maj avec un update dateEnvoie, updateDateCommande,etc...
         * on insert le current utilisateur
         * on insert les frais associés à la commande
         * on set le status de commande à "commandé" : statusCommande.get(0)
         * */

        CommandeEntity commandeEntity = CommandeEntity.builder()
                .dateCommande(Timestamp.from(Instant.now()))
                .dateEnvoie(Timestamp.valueOf("1990-01-01 00:00:00"))
                .dateLivraison(Timestamp.valueOf("1990-01-01 00:00:00"))
                .user(utilisateur)
                .listFrais(newFraisLabel)
                .statusDeCommande(statusCommande.get(0))
                .build();

        //Attention le prixUnitaire doit être celui du produit au moment de la commande
        //Donc il faut aller chercher le Produit en BDD
        Optional<ProduitEntity> produitEntity = Optional.of(iProduitRepository.getById(ligneCommandeDto.produitDto().idProduit()));
        //TODO : Gérer les exceptions si le Produit n'est pas retrouvée en BDD ou si le produit est désactivé
        if(produitEntity.isEmpty() || produitEntity.get().isDisable()){
            throw new NotFoundException();
        }
        BigDecimal prixUnitaireFromProduit = produitEntity.get().getPrix();
        LigneCommandeEntity ligneCommande = LigneCommandeEntity.builder()
                .quantite(ligneCommandeDto.quantite())
                .produits(produitEntity.get())
                .commandeEntity(commandeEntity)
                .prixUnitaire(prixUnitaireFromProduit)
                .build();

        ligneCommandeDto = lignCommandeMapper.toDto(iLigneCmdRepo.save(ligneCommande));
        return ligneCommandeDto;
    }



    @Override
    public void delete(int id) {
        Optional<LigneCommandeEntity> ligneCommande = iLigneCmdRepo.findById(id);
        if(ligneCommande.isPresent()){
            iLigneCmdRepo.deleteById(id);
        }else{
            throw new NotFoundExceptionWithMsg("La ligne de commande avec l'id : "+id+" n'existe pas");
        }
    }

    @Override
    public LigneCommandeDto update(int id, LigneCommandeDto lCmdQte) {
        return null;
    }

    private static List<FraisEntity> keepOnlyNewFrais(FraisEntity fraisFromBB, String labelToCheck, float prixAssocieFrais) {
        List<FraisEntity> result = new ArrayList<>();

        if (!labelToCheck.equalsIgnoreCase(fraisFromBB.getLabel())) {
            result.add(FraisEntity.builder()
                    .label(labelToCheck)
                    .montant(prixAssocieFrais)
                    .build());
        }

        return result;
    }
    public static List<String> removeDupList(List<String>list, boolean ignoreCase){
        Set<String> set = (ignoreCase?new TreeSet<String>(String.CASE_INSENSITIVE_ORDER):new LinkedHashSet<String>());
        set.addAll(list);

        List<String> res = new ArrayList<String>(set);
        return res;
    }

}

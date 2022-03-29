package fr.insys.commerce.mapper;

import fr.insys.commerce.dto.LigneCommandeDto;
import fr.insys.commerce.models.CommandeEntity;
import fr.insys.commerce.models.LigneCommandeEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RequiredArgsConstructor
public class LignCommandeMapper { //TODO : ne pas utiliser ce mapper pour le moment

    private final CommandeMapper commandeMapper;
    private final ProduitMapper produitMapper;

    public LigneCommandeDto toDto(LigneCommandeEntity ligneCommande){
        return new LigneCommandeDto(
                ligneCommande.getQuantite(),
                produitMapper.toDto(ligneCommande.getProduits()),
                commandeMapper.toDto(ligneCommande.getCommandeEntity()),
                ligneCommande.getPrixUnitaire()
        );
    }
    public LigneCommandeEntity toEntity(LigneCommandeDto ligneCommandeDto){
        return LigneCommandeEntity.builder()
                .quantite(ligneCommandeDto.quantite())
                .produits(this.produitMapper.toEntity(ligneCommandeDto.produitDto()))

                .build();

    }
}

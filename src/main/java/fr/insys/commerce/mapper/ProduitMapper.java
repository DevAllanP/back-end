package fr.insys.commerce.mapper;

import fr.insys.commerce.dto.ProduitDto;
import fr.insys.commerce.dto.TagDto;
import fr.insys.commerce.dto.TypeProduitDto;
import fr.insys.commerce.models.ProduitEntity;
import fr.insys.commerce.models.TagEntity;
import fr.insys.commerce.models.TypeProduitEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProduitMapper {
    private final TagMapper tagMapper;
    private final TypeProduitMapper typeProduitMapper;

    public ProduitDto toDto (ProduitEntity produit){
        Set<TagDto> tagDtos = null;
        TypeProduitDto typeProduitDto = typeProduitMapper.toDto(produit.getTypeProduitEntity());
        if(produit.getListTag() != null)
        	tagDtos = produit.getListTag().stream().map(tagMapper::toDto).collect(Collectors.toSet());
        return new ProduitDto(
                produit.getId(),
                produit.isDisable(),
                produit.getNom(),
                produit.getDescription(),
                produit.getPrix(),
                tagDtos,
                typeProduitDto);
    }

    public ProduitEntity toEntity(ProduitDto produitDto){
        Set<TagEntity> tags = null;
        TypeProduitEntity typeProduitEntity = typeProduitMapper.toEntity(produitDto.typeProduitDto());
        if(produitDto.tagDtos() != null)
        	tags = produitDto.tagDtos().stream().map(tagMapper::toEntity).collect(Collectors.toSet());
        ProduitEntity produit = ProduitEntity.builder()
                .prix(produitDto.prix())
                .description(produitDto.description())
                .disable(produitDto.disable())
                .nom(produitDto.nom())
                .listTag(tags)
                .typeProduitEntity(typeProduitEntity)
                .build();
        if(produitDto.idProduit() != null)
        	produit.setId(produitDto.idProduit());
        return produit;
    }
}

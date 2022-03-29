package fr.insys.commerce.mapper;

import fr.insys.commerce.dto.TypeProduitDto;
import fr.insys.commerce.dto.TypeProduitWithIdDto;
import fr.insys.commerce.models.TypeProduitEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TypeProduitMapper {
    public TypeProduitDto toDto(TypeProduitEntity typeProduitEntity){
        return new TypeProduitDto(typeProduitEntity.getLabel());
    }
    public TypeProduitWithIdDto toDtoWithId(TypeProduitEntity typeProduitEntity){
        return new TypeProduitWithIdDto(typeProduitEntity.getId(), typeProduitEntity.getLabel());
    }
    public TypeProduitEntity toEntity(TypeProduitDto typeProduitDto){
        return TypeProduitEntity.builder().label(typeProduitDto.label()).build();
    }
}
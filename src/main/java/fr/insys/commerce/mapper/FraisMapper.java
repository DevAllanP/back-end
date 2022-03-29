package fr.insys.commerce.mapper;

import fr.insys.commerce.dto.FraisDto;
import fr.insys.commerce.dto.FraisWithIdDto;
import fr.insys.commerce.models.FraisEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FraisMapper {
    public FraisEntity toEntity(FraisDto fraisDto){
        return FraisEntity.builder()
                .label(fraisDto.label())
                .montant(fraisDto.montant())
                .build();
    }
    public FraisDto toDto(FraisEntity fraisEntity){
        return new FraisDto(fraisEntity.getLabel(), fraisEntity.getMontant());
    }
    public FraisWithIdDto toDtoWithId(FraisEntity fraisEntity){
        return new FraisWithIdDto(fraisEntity.getId(), fraisEntity.getLabel(), fraisEntity.getMontant());
    }
}
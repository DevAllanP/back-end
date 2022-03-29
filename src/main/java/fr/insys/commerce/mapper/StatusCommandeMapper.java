package fr.insys.commerce.mapper;


import fr.insys.commerce.dto.StatusCommandeDto;
import fr.insys.commerce.dto.StatusCommandeDtoWithLabel;
import fr.insys.commerce.models.StatusCommandeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatusCommandeMapper {
    public StatusCommandeEntity toEntity (StatusCommandeDtoWithLabel statusCommandeDto){
        return StatusCommandeEntity.builder()
                .label(statusCommandeDto.label())
                .build();
    }

    public StatusCommandeDtoWithLabel toDto(StatusCommandeEntity statusCommandeEntity){
        return new StatusCommandeDtoWithLabel(
                statusCommandeEntity.getLabel()
        );
    }

    public StatusCommandeDto toDtoWithId(StatusCommandeEntity statusCommandeEntity) {
        return new StatusCommandeDto(statusCommandeEntity.getId(), statusCommandeEntity.getLabel());

    }
}

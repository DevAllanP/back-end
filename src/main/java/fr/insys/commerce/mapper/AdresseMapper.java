package fr.insys.commerce.mapper;

import fr.insys.commerce.dto.AdresseDto;
import fr.insys.commerce.models.AdresseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdresseMapper {

    public AdresseEntity toEntity(AdresseDto adresseDto){
        return AdresseEntity.builder()
                .numero(adresseDto.numero())
                .rue(adresseDto.rue())
                .ville(adresseDto.ville())
                .codePostal(adresseDto.codePostal())
                .build();
    }
    public AdresseEntity toEntity(AdresseDto adresseDto, int id){
        return AdresseEntity.builder().id(id)
                .numero(adresseDto.numero())
                .rue(adresseDto.rue())
                .ville(adresseDto.ville())
                .codePostal(adresseDto.codePostal())
                .build();
    }
    
    public AdresseDto toDto(AdresseEntity adresseEntity){
        return new AdresseDto(
                adresseEntity.getNumero(),
                adresseEntity.getRue(),
                adresseEntity.getVille(),
                adresseEntity.getCodePostal()
        );
    }
    
    
}

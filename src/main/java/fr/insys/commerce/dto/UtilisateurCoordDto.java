package fr.insys.commerce.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public record UtilisateurCoordDto (
        @NotBlank @Email String email,
        AdresseDto adresse


){
}

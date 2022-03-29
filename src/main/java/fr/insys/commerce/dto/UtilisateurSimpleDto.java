package fr.insys.commerce.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


public record UtilisateurSimpleDto (
        @NotBlank String nom,
        @NotBlank String prenom,
        @NotBlank @Email String email,
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) @NotBlank String password,
        @NotNull @JsonFormat(pattern = "yyyy-MM-dd")Timestamp dateNaissance,
        boolean disable,
        @JsonIgnore RoleDto role,
        AdresseDto adresseDto

){
}

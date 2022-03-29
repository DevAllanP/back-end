package fr.insys.commerce.dto;

import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import fr.insys.commerce.models.ERole;

public record UtilisateurDto(
		@NotBlank String nom,
		@NotBlank String prenom,
		@NotBlank @Email String email,
		@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) @NotBlank String password,
		@NotNull @JsonFormat(pattern = "yyyy-MM-dd") Timestamp dateNaissance,
		Boolean disable, @JsonIgnore RoleDto role,
		@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) Integer roleId,
		AdresseDto adresse,
		@JsonInclude(JsonInclude.Include.NON_NULL) String accessToken,
		@JsonInclude(JsonInclude.Include.NON_NULL) ERole roleLabel
) {

	}
package fr.insys.commerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_utilisateur_user")
public class UtilisateurEntity {
	@Id
	@Column(name = "user_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "user_nom")
	private String nom;

	@Column(name = "user_prenom")
	private String prenom;

	@Column(name = "user_email", unique = true)
	private String email;

	@Column(name = "user_password")
	private String password;

	@Column(name = "user_naissance")
	private Timestamp dateNaissance;

	@Column(name = "user_disable")
	private Boolean disable;

	@ManyToOne
	private RoleEntity role;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private AdresseEntity adresseEntity;


}
package fr.insys.commerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_adresse_adr")
public class AdresseEntity {
	@Id
	@Column(name = "adr_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "adr_num")
	private int numero;

	@Column(name = "adr_rue")
	private String rue;

	@Column(name = "adr_ville")
	private String ville;

	@Column(name = "adr_codepostal")
	private String codePostal;
}
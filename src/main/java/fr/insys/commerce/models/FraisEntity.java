package fr.insys.commerce.models;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Builder
@Entity
@Table(name = "t_frais_frs")
public class FraisEntity {

	@Id
	@Column(name = "frs_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name ="frs_label", unique = true)
	private String label;

	@Column(name = "frs_montant")
	private float montant;

	

	
	public FraisEntity() {
		super();
	}

	public FraisEntity(int id, String label, float montant) {
		super();
		this.id = id;
		this.label = label;
		this.montant = montant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public float getMontant() {
		return montant;
	}

	public void setMontant(float montant) {
		this.montant = montant;
	}
}

package fr.insys.commerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_lignecommande_lgc")
public class LigneCommandeEntity {
	@Id
	@Column(name = "lgc_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "lgc_qte")
	private int quantite;

	@ManyToOne
	private ProduitEntity produits;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private CommandeEntity commandeEntity;

	/*prix du produit au moment de l'acaht*/
	@Column(name="lgc_prix_unitaire")
	private BigDecimal prixUnitaire;


}

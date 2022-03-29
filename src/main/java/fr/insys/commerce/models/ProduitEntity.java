package fr.insys.commerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.*;

import org.javamoney.moneta.Money;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_produit_prd")
public class ProduitEntity {

	@Id
	@Column(name = "prd_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "prd_disable")
	private boolean disable;

	@Column(name = "prd_nom")
	private String nom;

	@Column(name = "prd_description", columnDefinition="TEXT")
	private String description;

	@Column(name = "prd_prix")
	private BigDecimal prix;

	
	
	@OneToOne
	@JoinColumn( name="idTypeProduit" )
	private TypeProduitEntity typeProduitEntity;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "t_tag_tag_list_produit",
			joinColumns = {@JoinColumn(name = "list_produit_prd_id")},
			inverseJoinColumns = {@JoinColumn(name = "tag_id")})
	private Set<TagEntity> listTag;

	
	
	
}

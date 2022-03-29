package fr.insys.commerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="t_commande_cmd")
public class CommandeEntity {
	@Id
	@Column(name = "cmd_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@Column(name = "cmd_dtcmd")
	public Timestamp dateCommande; 
	
	@Column(name = "cmd_dtenvoie")
	public Timestamp dateEnvoie;
	
	@Column(name = "cmd_dtlivraison")
	public Timestamp dateLivraison;


	//Pour save la Commande le cascade Type doit être en Merge sinon Pb de "detached entity passed to persist"
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private UtilisateurEntity user;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<FraisEntity> listFrais;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private StatusCommandeEntity statusDeCommande;


}
package fr.insys.commerce.models;

import lombok.*;

import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_tag_tag")
public class TagEntity {

	@Id
	@Column(name = "tag_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "tag_label")
	private String label;

	@ManyToMany(mappedBy = "listTag")
	/*@JsonIgnore*/
	private Set<ProduitEntity> listProduit;
}

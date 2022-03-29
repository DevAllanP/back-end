package fr.insys.commerce.models;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_role_rol")
public class RoleEntity {
	@Id
	@Column(name = "rol_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
	@Column(length = 20, name = "rol_label", unique = true)
	private ERole label;
}
package fr.insys.commerce.mapper;

import org.springframework.stereotype.Component;
import fr.insys.commerce.dto.RoleDto;
import fr.insys.commerce.models.ERole;
import fr.insys.commerce.models.RoleEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RoleMapper {
	public RoleDto toDto(RoleEntity role) {
		return new RoleDto(role.getId(), role.getLabel().name());
	}

	public RoleEntity toEntity(RoleDto role) {
		return RoleEntity.builder()
				.id(role.id())
				.label(ERole.valueOf(role.label()))
				.build()
		;
	}
}
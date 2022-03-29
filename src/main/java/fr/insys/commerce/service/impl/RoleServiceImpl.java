package fr.insys.commerce.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import fr.insys.commerce.dto.RoleDto;
import fr.insys.commerce.repository.RoleRepository;
import fr.insys.commerce.service.RoleService;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
	private final RoleRepository roleRepository;

	@Override
	public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(r -> new RoleDto(r.getId(), r.getLabel().name()))
                .collect(Collectors.toList());
	}
}
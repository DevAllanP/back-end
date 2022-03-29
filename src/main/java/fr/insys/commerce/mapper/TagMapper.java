package fr.insys.commerce.mapper;

import fr.insys.commerce.dto.TagDto;
import fr.insys.commerce.dto.TagWithIdDto;
import fr.insys.commerce.models.TagEntity;
import fr.insys.commerce.repository.ITagRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagMapper {
	private final ITagRepository tagRepo;
	
	public TagDto toDto(TagEntity tag) {
		return new TagDto(tag.getLabel());
	}

	public TagWithIdDto toDtoWithId(TagEntity tag) {
		return new TagWithIdDto(tag.getId(), tag.getLabel());
	}

	public TagEntity toEntity(TagDto tagDto) {
		return tagRepo.findByLabel(tagDto.label()).orElse(TagEntity.builder().label(tagDto.label()).build());
	}
}
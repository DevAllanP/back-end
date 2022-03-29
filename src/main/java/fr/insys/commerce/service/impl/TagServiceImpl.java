package fr.insys.commerce.service.impl;

import fr.insys.commerce.dto.TagWithIdDto;
import fr.insys.commerce.dto.TagDto;
import fr.insys.commerce.exceptions.NotFoundException;
import fr.insys.commerce.mapper.TagMapper;
import fr.insys.commerce.models.TagEntity;
import fr.insys.commerce.models.TypeProduitEntity;
import fr.insys.commerce.repository.ITagRepository;
import fr.insys.commerce.repository.ITypeProduitRepository;
import fr.insys.commerce.service.TagService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final ITagRepository tagRepository;
    private final ITypeProduitRepository typeProduitRepo;
    private final TagMapper tagMapper;

    @Override
    public List<TagDto> findAll() {
        return tagRepository.findAll().stream()
                .map(tagMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TagDto save(TagDto tagDto) {
        TagEntity tag = tagMapper.toEntity(tagDto);
        tagRepository.save(tag);
        return tagMapper.toDto(tag);
    }

    @Override
    public TagDto getById(int id) {
        //TODO : Check if authorized before submitting
        TagEntity tag = tagRepository.findById(id).orElseThrow(NotFoundException::new);
        return tagMapper.toDto(tag);
    }

    @Override
    public void delete(int id) {
        tagRepository.findById(id).orElseThrow(NotFoundException::new);
        tagRepository.deleteById(id);
    }

    @Override
    public TagDto update(int id, TagDto tagDto) {
        TagEntity tag = tagRepository.findById(id).orElseThrow(NotFoundException::new);
        tag.setLabel(tagDto.label());
        tagRepository.save(tag);
        return tagMapper.toDto(tag);
    }

	@Override
	public List<TagWithIdDto> findAllWithId() {
        return tagRepository.findAll().stream()
                .map(tagMapper::toDtoWithId)
                .collect(Collectors.toList());
	}

	@Override
	public Iterable<TagDto> findAllByTypeProduit(String type) {
		TypeProduitEntity typeProduit = typeProduitRepo.findByLabel(type).orElseThrow(NotFoundException::new);
		return tagRepository.findAllByTypeProduit(typeProduit).stream().map(tagMapper::toDto).collect(Collectors.toList());
	}
}
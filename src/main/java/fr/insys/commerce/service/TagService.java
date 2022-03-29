package fr.insys.commerce.service;

import fr.insys.commerce.dto.TagWithIdDto;
import fr.insys.commerce.dto.TagDto;

import java.util.List;

public interface TagService {
    List<TagDto> findAll();
    List<TagWithIdDto> findAllWithId();
    TagDto save (TagDto tagDto);
    TagDto getById(int id);
    void delete (int id);
    TagDto update (int id, TagDto tagDto);
	Iterable<TagDto> findAllByTypeProduit(String type);
}

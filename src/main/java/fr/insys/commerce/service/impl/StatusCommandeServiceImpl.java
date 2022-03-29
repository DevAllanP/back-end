package fr.insys.commerce.service.impl;


import fr.insys.commerce.dto.FraisDto;
import fr.insys.commerce.dto.StatusCommandeDto;
import fr.insys.commerce.dto.StatusCommandeDtoWithLabel;
import fr.insys.commerce.exceptions.NotFoundException;
import fr.insys.commerce.mapper.StatusCommandeMapper;
import fr.insys.commerce.models.StatusCommandeEntity;
import fr.insys.commerce.repository.IStatusCommandeRepository;
import fr.insys.commerce.service.StatusCommandeService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
public class StatusCommandeServiceImpl implements StatusCommandeService {
    private final StatusCommandeMapper statusCommandeMapper;
    private final IStatusCommandeRepository iStatusCommandeRepository;

    @Override
    public List<StatusCommandeDtoWithLabel> findAll() {
        return iStatusCommandeRepository.findAll().stream().map(statusCommandeMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public StatusCommandeDtoWithLabel save(@Valid StatusCommandeDtoWithLabel statusCommandeDto) {

        if(checkIfStatusAlreadyExists(statusCommandeDto)){
            //TODO : remonter une erreure personnalisée : le label existe déja
            throw new NotFoundException();
        }
        StatusCommandeEntity statusCommandeEntity = new StatusCommandeEntity();
        statusCommandeEntity.setLabel(statusCommandeDto.label());//TODO : Builder
        statusCommandeDto = statusCommandeMapper.toDto(iStatusCommandeRepository.save(statusCommandeEntity));
        return statusCommandeDto;
    }

    @Override
    public StatusCommandeDtoWithLabel getById(int id) {
        StatusCommandeEntity statusCommande = iStatusCommandeRepository.findById(id).orElseThrow(NotFoundException::new);
        return new StatusCommandeDtoWithLabel(statusCommande.getLabel());
    }

    @Override
    public void delete(int id) {
        Optional<StatusCommandeEntity> statusCommandeDao = iStatusCommandeRepository.findById(id);
        if(statusCommandeDao.isEmpty() ){
            throw new NotFoundException();
        }else{
            iStatusCommandeRepository.deleteById(id);
        }

    }

    @Override
    public StatusCommandeDtoWithLabel update(int id, StatusCommandeDtoWithLabel statusCommandeDto) {
        StatusCommandeEntity statusCommande = iStatusCommandeRepository.findById(id).orElseThrow(NotFoundException::new);
        statusCommande.setLabel(statusCommandeDto.label());
        iStatusCommandeRepository.save(statusCommande);
        return statusCommandeMapper.toDto(statusCommande);
    }
    @Override
	public List<StatusCommandeDto> findAllWithId() {
        return iStatusCommandeRepository.findAll().stream()
                .map(statusCommandeMapper::toDtoWithId)
                .collect(Collectors.toList());
	}

        public boolean checkIfStatusAlreadyExists(StatusCommandeDtoWithLabel statusCommandeDtoWithLabel){
        AtomicBoolean exists = new AtomicBoolean(false);
        iStatusCommandeRepository.findAll().stream().forEach(f->{
            if(f.getLabel().equalsIgnoreCase(statusCommandeDtoWithLabel.label())
                    && !statusCommandeDtoWithLabel.label().equals("")
            ){
                exists.set(true);
            }
        });
        return exists.get();
    }
}
package fr.insys.commerce.service.impl;

import fr.insys.commerce.dto.FraisWithIdDto;
import fr.insys.commerce.dto.FraisDto;
import fr.insys.commerce.exceptions.NotFoundException;
import fr.insys.commerce.mapper.FraisMapper;
import fr.insys.commerce.models.FraisEntity;
import fr.insys.commerce.repository.IFraisRepository;
import fr.insys.commerce.service.FraisService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
public class FraisServiceImpl implements FraisService {
    private final FraisMapper fraisMapper;
    private final IFraisRepository fraisRepository;

    @Override
    public List<FraisDto> findAll() {
        return fraisRepository.findAll().stream()
                .map(fraisMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FraisDto save(FraisDto fraisDto) {
        //valeur unique pour chaque label en ignoreCaseSensitivity
        FraisEntity frais = FraisEntity.builder()
                .montant(fraisDto.montant())
                .label(fraisDto.label())
                .build();
        if(!checkIfLabelFraisExists(fraisDto)){
            fraisRepository.save(frais);
        }else{
            throw new NotFoundException();
        }
        return fraisMapper.toDto(frais);
    }

    @Override
    public FraisDto getById(int id) {
        FraisEntity frais = fraisRepository.findById(id).orElseThrow(NotFoundException::new);
        return fraisMapper.toDto(frais);
    }

    @Override
    public void delete(int id) {
        fraisRepository.findById(id).orElseThrow(NotFoundException::new);
        fraisRepository.deleteById(id);
    }

    @Override
    public FraisDto update(int id, FraisDto fraisDto) {
        FraisEntity frais = fraisRepository.findById(id).orElseThrow(NotFoundException::new);
        FraisWithIdDto fraisWithIdDto = new FraisWithIdDto(id, fraisDto.label(), fraisDto.montant());
        if(!checkifFraisIsUpdatable(fraisWithIdDto)){
            log.debug("Label Existe");
            frais.setLabel(fraisDto.label());
            frais.setMontant(fraisDto.montant());
            fraisRepository.save(frais);
        }else{
            log.debug("Label Inexistant");
            throw new NotFoundException();
        }
        return fraisMapper.toDto(frais);
    }

	@Override
	public List<FraisWithIdDto> findAllWithId() {
        return fraisRepository.findAll().stream()
                .map(fraisMapper::toDtoWithId)
                .collect(Collectors.toList());
	}
    public boolean checkIfLabelFraisExists(FraisDto fraisDto){
        AtomicBoolean exists = new AtomicBoolean(false);
        fraisRepository.findAll().stream().forEach(f->{
            if(f.getLabel().equalsIgnoreCase(fraisDto.label())
                    && !fraisDto.label().equals("")
            ){
                exists.set(true);
            }
        });
        return exists.get();
    }
    public boolean checkifFraisIsUpdatable(FraisWithIdDto fraisDto){
        AtomicBoolean exists = new AtomicBoolean(false);
        fraisRepository.findAll().stream().forEach(f->{
            if( (f.getLabel().equalsIgnoreCase(fraisDto.label()))
                    && ! (f.getId()==fraisDto.id())
            ){
                exists.set(true);
            }
        });
        return exists.get();
    }
}

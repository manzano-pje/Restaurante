package com.projeto.restaurante.service;

import com.projeto.restaurante.configuration.TextConverter;
import com.projeto.restaurante.dto.SectionDto;
import com.projeto.restaurante.exceptions.AlreadySectionRegisteredException;
import com.projeto.restaurante.exceptions.UnregisteredSectionException;
import com.projeto.restaurante.identities.Section;
import com.projeto.restaurante.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SectionService {

    private final SectionRepository sectionRepository;
    private final ModelMapper mapper;

    public SectionDto create(SectionDto sectionDto){
        Optional<Section> sectionOptional = sectionRepository.findByName(sectionDto.getClass().getName());
        if(sectionOptional.isPresent()){
            throw new AlreadySectionRegisteredException();
        }
        Section section = mapper.map(sectionDto, Section.class);
        section.setName(TextConverter.stringConverter(section.getName()));
        sectionRepository.save(section);
        return mapper.map(section, SectionDto.class);
    }

    public List<SectionDto> findAll(){
        List<Section> sectionList = sectionRepository.findAll();
        if(sectionList.isEmpty()){
            throw new UnregisteredSectionException();
        }
        return sectionList.stream().map(SectionDto::new).collect(Collectors.toList());
    }

    public SectionDto findOneSection(String section){
        Optional<Section> sectionOptional = sectionRepository.findByName(section);
        if(sectionOptional.isEmpty()){
            throw new UnregisteredSectionException();
        }
        return mapper.map(sectionOptional, SectionDto.class);
    }

    public void update(String sectionName, SectionDto sectionDto){
        Optional<Section> sectionOptional = sectionRepository.findByName(sectionName);
        if(sectionOptional.isEmpty()){
            throw new UnregisteredSectionException();
        }
        Section section = mapper.map(sectionOptional, Section.class);
        section.setId(sectionOptional.get().getId());
        section.setName(TextConverter.stringConverter(sectionDto.getName()));
        sectionRepository.save(section);
    }

}

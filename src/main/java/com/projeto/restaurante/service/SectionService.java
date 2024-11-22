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

import java.util.Optional;

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
}

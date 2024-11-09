package com.projeto.restaurante.service;

import com.projeto.restaurante.configuration.TextConverter;
import com.projeto.restaurante.dto.SeatingDto;
import com.projeto.restaurante.exceptions.EmptySeatingListException;
import com.projeto.restaurante.exceptions.SeatingAlreadyRegisteredException;
import com.projeto.restaurante.exceptions.UnregisteredSeatingException;
import com.projeto.restaurante.identities.Seating;
import com.projeto.restaurante.repository.SeatingRepository;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SeatingService {

    private final SeatingRepository repository;
    private final ModelMapper mapper;

    public SeatingDto create(SeatingDto seatingDto) {
        Optional<Seating> seatingOptional = repository.findByName(seatingDto.getName());
        if (seatingOptional.isPresent()) {
            throw new SeatingAlreadyRegisteredException();
        }
        Seating seating = mapper.map(seatingDto, Seating.class);
        seating.setName(TextConverter.stringConverter(seatingDto.getName()));
        repository.save(seating);
        return mapper.map(seating, SeatingDto.class);
    }

    public List<SeatingDto> findAll() {
        List<Seating> seatingList = repository.findAll();
        if (seatingList.isEmpty()) {
            throw new EmptySeatingListException();
        }
        return seatingList.stream().
                map(SeatingDto::new).
                sorted(Comparator.comparing(SeatingDto::getName)).
                collect(Collectors.toList());
    }

    public SeatingDto findOne(String name) {
        Optional<Seating> seatingOptional = repository.findByName(name);
        if (seatingOptional.isEmpty()) {
            throw new UnregisteredSeatingException();
        }
        return mapper.map(seatingOptional, SeatingDto.class);
    }

    public void update(String name, String nameUpdate) {
        Optional<Seating> seatingOptional = repository.findByName(name);
        if (seatingOptional.isEmpty()) {
            throw new UnregisteredSeatingException();
        }
        Optional<Seating> nameUpdateOptional = repository.findByName(nameUpdate);
        if(nameUpdateOptional.isPresent()) {
            throw new SeatingAlreadyRegisteredException();
        }
        Seating seating = mapper.map(seatingOptional, Seating.class);
        seating.setName(TextConverter.stringConverter(nameUpdate));
        repository.save(seating);
    }

    public void delete(String name) {
        Optional<Seating> seatingOptional = repository.findByName(name);
        if (seatingOptional.isEmpty()) {
            throw new UnregisteredSeatingException();
        }
        repository.deleteById(seatingOptional.get().getId());
    }
}
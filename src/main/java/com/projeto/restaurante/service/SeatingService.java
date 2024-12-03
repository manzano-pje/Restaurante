package com.projeto.restaurante.service;

import com.projeto.restaurante.configuration.TextConverter;
import com.projeto.restaurante.dto.ReturnRequestDto;
import com.projeto.restaurante.dto.SeatingDto;
import com.projeto.restaurante.exceptions.*;
import com.projeto.restaurante.identities.Request;
import com.projeto.restaurante.identities.Seating;
import com.projeto.restaurante.repository.RequestRepository;
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

    private final SeatingRepository seatingRepository;
    private final RequestRepository requestRepository;
    private final ModelMapper mapper;

    public SeatingDto create(SeatingDto seatingDto) {
        Optional<Seating> seatingOptional = seatingRepository.findByName(seatingDto.getName());
        if (seatingOptional.isPresent()) {
            throw new SeatingAlreadyRegisteredException();
        }
        Seating seating = mapper.map(seatingDto, Seating.class);
        seating.setName(TextConverter.stringConverter(seatingDto.getName()));
        seatingRepository.save(seating);
        return mapper.map(seating, SeatingDto.class);
    }

    public List<SeatingDto> findAll() {
        List<Seating> seatingList = seatingRepository.findAll();
        if (seatingList.isEmpty()) {
            throw new EmptySeatingListException();
        }
        return seatingList.stream().
                map(SeatingDto::new).
                sorted(Comparator.comparing(SeatingDto::getName)).
                collect(Collectors.toList());
    }

    public SeatingDto findOne(String name) {
        Optional<Seating> seatingOptional = seatingRepository.findByName(name);
        if (seatingOptional.isEmpty()) {
            throw new UnregisteredSeatingException();
        }
        return mapper.map(seatingOptional, SeatingDto.class);
    }

    public void update(String name, String nameUpdate) {
        Optional<Seating> seatingOptional = seatingRepository.findByName(name);
        if (seatingOptional.isEmpty()) {
            throw new UnregisteredSeatingException();
        }
        Optional<Seating> nameUpdateOptional = seatingRepository.findByName(nameUpdate);
        if(nameUpdateOptional.isPresent()) {
            throw new SeatingAlreadyRegisteredException();
        }
        Seating seating = mapper.map(seatingOptional, Seating.class);
        seating.setName(TextConverter.stringConverter(nameUpdate));
        seatingRepository.save(seating);
    }

    public void delete(String name) {
        Optional<Seating> seatingOptional = seatingRepository.findByName(name);
        if (seatingOptional.isEmpty()) {
            throw new UnregisteredSeatingException();
        }
        seatingRepository.deleteById(seatingOptional.get().getId());
    }

    public ReturnRequestDto listRequestBySeating(int seatingId){
        List<Request> requestList = requestRepository.findRequestsBySeatingIdAndStatusTrue(seatingId);
        if(requestList.isEmpty()){
            throw new UnregisteredSeatingException();
        }
        Seating seating = requestList.get(0).getRequestSeating();
        return new ReturnRequestDto(seating, requestList);
    }
}
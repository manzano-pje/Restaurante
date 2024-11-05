package com.projeto.restaurante.service;

import com.projeto.restaurante.configuration.TextConverter;
import com.projeto.restaurante.dto.AttendantDto;
import com.projeto.restaurante.exceptions.AttendantAlreadyRegisteredExceptoin;
import com.projeto.restaurante.exceptions.UnregisteredAttendantExceptoin;
import com.projeto.restaurante.identities.Attendant;
import com.projeto.restaurante.repository.AttendantRepositpry;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.projeto.restaurante.configuration.TextConverter.stringConverter;

@Service
@AllArgsConstructor
public class AttendantService {

    private final AttendantRepositpry repositpry;
    private final ModelMapper mapper;

    public AttendantDto create(AttendantDto attendantDto){

        Optional<Attendant> attendantOptional = repositpry.findByName(attendantDto.getName());
        if(attendantOptional.isPresent()){
            throw new AttendantAlreadyRegisteredExceptoin();
        }
        attendantDto.setName(stringConverter(attendantDto.getName()));
        Attendant attendant = mapper.map(attendantDto, Attendant.class);
        repositpry.save(attendant);
        return attendantDto;
    }

    public List<AttendantDto> findAll(){
        List<Attendant> attendantList = repositpry.findAll();
        if(attendantList.isEmpty()){
            throw new UnregisteredAttendantExceptoin();
        }
        return attendantList.stream().
                map(AttendantDto::new).
                collect(Collectors.toList());
    }

    public List<AttendantDto> findByName(String name){
        List<Attendant> attendantList = repositpry.findAllByName(name);
        if(attendantList.isEmpty()){
            throw new UnregisteredAttendantExceptoin();
        }
        return attendantList.
                stream().
                map(AttendantDto::new).
                collect(Collectors.toList());
    }


}

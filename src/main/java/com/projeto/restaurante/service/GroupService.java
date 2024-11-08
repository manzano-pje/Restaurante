package com.projeto.restaurante.service;

import com.projeto.restaurante.configuration.TextConverter;
import com.projeto.restaurante.dto.GroupDto;
import com.projeto.restaurante.exceptions.GroupAlreadyRegisteredException;
import com.projeto.restaurante.identities.Group;
import com.projeto.restaurante.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository repository;
    private final ModelMapper mapper;

    public GroupDto create(GroupDto groupDto){
        Optional<Group> groupOptional = repository.findByName(groupDto.getName());
        if(groupOptional.isPresent()){
            throw new GroupAlreadyRegisteredException();
        }
        Group group = mapper.map(groupDto, Group.class);
        group.setName(TextConverter.stringConverter(groupDto.getName()));
        repository.save(group);
        return mapper.map(group, GroupDto.class);
    }
}

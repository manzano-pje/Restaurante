package com.projeto.restaurante.service;

import com.projeto.restaurante.configuration.TextConverter;
import com.projeto.restaurante.dto.GroupDto;
import com.projeto.restaurante.exceptions.EmptyGroupListException;
import com.projeto.restaurante.exceptions.GroupAlreadyRegisteredException;
import com.projeto.restaurante.exceptions.UnregisteredGroupException;
import com.projeto.restaurante.identities.Group;
import com.projeto.restaurante.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<GroupDto> listAll(){
        List<Group> groupList = repository.findAll();
        if (groupList.isEmpty()){
            throw new EmptyGroupListException();
        }
        return groupList.stream().
                map(GroupDto::new).
                collect(Collectors.toList());
    }

    public GroupDto findOne(String name){
        Optional<Group> optionalGroup = repository.findByName(name);
        if(optionalGroup.isEmpty()){
            throw new UnregisteredGroupException();
        }
        return mapper.map(optionalGroup, GroupDto.class);
    }

    public void update(String name, String groupUpdade){
        Optional<Group> groupOptional = repository.findByName(name);
        if(groupOptional.isEmpty()){
            throw new UnregisteredGroupException();
        }
        Group group = mapper.map(groupOptional, Group.class);
        group.setName(TextConverter.stringConverter(groupUpdade));
        repository.save(group);
    }

    public void delete(String name){
        Optional<Group> groupOptional = repository.findByName(name);
        if(groupOptional.isEmpty()){
            throw new UnregisteredGroupException();
        }
        groupOptional.ifPresent(repository::delete);
    }
}

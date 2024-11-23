package com.projeto.restaurante.service;

import com.projeto.restaurante.configuration.TextConverter;
import com.projeto.restaurante.dto.GroupDto;
import com.projeto.restaurante.exceptions.EmptyGroupListException;
import com.projeto.restaurante.exceptions.GroupAlreadyRegisteredException;
import com.projeto.restaurante.exceptions.UnregisteredGroupException;
import com.projeto.restaurante.exceptions.UnregisteredSectionException;
import com.projeto.restaurante.identities.Group;
import com.projeto.restaurante.identities.Section;
import com.projeto.restaurante.repository.GroupRepository;
import com.projeto.restaurante.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final SectionRepository sectionRepository;
    private final ModelMapper mapper;

    public GroupDto create(GroupDto groupDto){
        Optional<Group> groupOptional = groupRepository.findByName(groupDto.getName());
        if(groupOptional.isPresent()){
            throw new GroupAlreadyRegisteredException();
        }
        Optional<Section> sectionOptional = sectionRepository.findById(groupDto.getSectionId());
        if(sectionOptional.isEmpty()){
            throw new UnregisteredSectionException();
        }
        Group group = mapper.map(groupDto, Group.class);
        group.setName(TextConverter.stringConverter(groupDto.getName()));
        group.setGroupSection(sectionOptional.get());
        groupRepository.save(group);
        return mapper.map(group, GroupDto.class);
    }

    public List<GroupDto> listAll(){
        List<Group> groupList = groupRepository.findAll();
        if (groupList.isEmpty()){
            throw new EmptyGroupListException();
        }
        return groupList.stream().
                map(GroupDto::new).
                collect(Collectors.toList());
    }

    public GroupDto findOne(String name){
        Optional<Group> optionalGroup = groupRepository.findByName(name);
        if(optionalGroup.isEmpty()){
            throw new UnregisteredGroupException();
        }
        return mapper.map(optionalGroup, GroupDto.class);
    }

    public List<GroupDto> findGroupBySection(String section){
        Optional<Section> sectionOptional = sectionRepository.findByName(section);
        if(sectionOptional.isEmpty()){
            throw new UnregisteredSectionException();
        }
        List<Group> groupList = groupRepository.findByGroupSection_Id(sectionOptional.get().getId());
        if(groupList.isEmpty()){
            throw new UnregisteredGroupException();
        }
        return groupList.stream().map(GroupDto::new).collect(Collectors.toList());
    }

    public void update(String name, String groupUpdade){
        Optional<Group> groupOptional = groupRepository.findByName(name);
        if(groupOptional.isEmpty()){
            throw new UnregisteredGroupException();
        }
        Group group = mapper.map(groupOptional, Group.class);
        group.setName(TextConverter.stringConverter(groupUpdade));
        groupRepository.save(group);
    }

    public void delete(String name){
        Optional<Group> groupOptional = groupRepository.findByName(name);
        if(groupOptional.isEmpty()){
            throw new UnregisteredGroupException();
        }
        groupRepository.deleteById(groupOptional.get().getId());
    }
}

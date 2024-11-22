package com.projeto.restaurante.controller;

import com.projeto.restaurante.dto.GroupDto;
import com.projeto.restaurante.service.GroupService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/group")
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid GroupDto groupDto){
        GroupDto retorno = groupService.create(groupDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/group").
                buildAndExpand(retorno).toUri();
        return ResponseEntity.created(uri).body("Grupo criado com sucesso!");
    }

    @GetMapping
    public List<GroupDto> listAll(){
        return groupService.listAll();
    }

    @GetMapping("/{name}")
    public GroupDto findOne(@PathVariable String name){
        GroupDto groupDto = groupService.findOne(name);
        return groupDto;
    }

    @PatchMapping("/{name}")
    public ResponseEntity<Object> update(@PathVariable String name, String nameUpdate){
        groupService.update(name, nameUpdate);
        return ResponseEntity.status(HttpStatus.OK).body("Grupo atualizado com sucesso");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Object> delete(String name){
        groupService.delete(name);
        return ResponseEntity.status(HttpStatus.OK).body("Grupo excluído com sucesso");

    }
}

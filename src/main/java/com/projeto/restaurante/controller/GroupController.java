package com.projeto.restaurante.controller;

import com.projeto.restaurante.dto.GroupDto;
import com.projeto.restaurante.service.GroupService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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
}

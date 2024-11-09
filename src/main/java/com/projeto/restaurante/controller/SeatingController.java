package com.projeto.restaurante.controller;

import com.projeto.restaurante.dto.SeatingDto;
import com.projeto.restaurante.service.SeatingService;
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
@RequestMapping("/api/v1/seating")
public class SeatingController {

    private final SeatingService service;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid SeatingDto seatingDto){
        SeatingDto retorno = service.create(seatingDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("api/v1/seating").
                buildAndExpand(retorno).toUri();
        return  ResponseEntity.created(uri).body("Mesa criada com sucesso!");
    }

    @GetMapping
    public List<SeatingDto> findAll(){
        return service.findAll();
    }

    @GetMapping("/{name}")
    public SeatingDto findOne(@PathVariable String name){
        return service.findOne(name);
    }

    @PatchMapping("/{name}")
    public ResponseEntity<Object> update(@PathVariable String name, String nameUpdate){
        service.update(name, nameUpdate);
        return ResponseEntity.status(HttpStatus.OK).body("Nome da mesa alterado com sucesso!");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Object> delete(@PathVariable String name){
        service.delete(name);
        return ResponseEntity.status(HttpStatus.OK).body("Nome da mesa excluída com sucesso!");
    }
}

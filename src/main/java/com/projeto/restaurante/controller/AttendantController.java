package com.projeto.restaurante.controller;

import com.projeto.restaurante.dto.AttendantDto;
import com.projeto.restaurante.service.AttendantService;
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
@RequestMapping("api/v1/attendant")
public class AttendantController {

    private final AttendantService attendantService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid AttendantDto attendantDto) {
        AttendantDto retorno = attendantService.create(attendantDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("api/v1").
                buildAndExpand(retorno).toUri();
        return ResponseEntity.created(uri).body("Atendente criado com sucesso!");
    }

    @GetMapping
    public List<AttendantDto> findAll(){
        return attendantService.findAll();
    }

    @GetMapping("/{name}")
    public List<AttendantDto> findOne(String name){
        return attendantService.findByName(name);
    }

    @PatchMapping("/{name}")
    public ResponseEntity<Object> update(@PathVariable String name, String nameUpdate){
        attendantService.update(name, nameUpdate);
        return ResponseEntity.status(HttpStatus.OK).body("Atendente atualizado com sucesso");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Object> delete(@PathVariable String name){
        attendantService.delete(name);
        return ResponseEntity.status(HttpStatus.OK).body("Atendente apagado com sucesso");
    }

}

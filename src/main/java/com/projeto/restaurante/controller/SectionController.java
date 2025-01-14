package com.projeto.restaurante.controller;

import com.projeto.restaurante.dto.SectionDto;
import com.projeto.restaurante.service.SectionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/section")
public class SectionController {

    private final SectionService sectionService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid SectionDto sectionDto){
        SectionDto retorno = sectionService.create(sectionDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/section").
                buildAndExpand(retorno).toUri();
        return ResponseEntity.created(uri).body("Seção criada com sucesso");
    }

    @GetMapping
    public List<SectionDto> findAll(){
        return sectionService.findAll();
    }

    @GetMapping("/{section}")
    public SectionDto findOneSection(@PathVariable String section){
        return sectionService.findOneSection(section);
    }

    @PatchMapping("/{section}")
    public ResponseEntity<Object> update(@PathVariable String section, @RequestBody @Valid SectionDto sectionDto){
        sectionService.update(section, sectionDto);
        return ResponseEntity.status(HttpStatus.OK).body("Seção atualizada com sucesso!");
    }

    @DeleteMapping("/{section}")
    public ResponseEntity<Object> delete(@PathVariable String section){
        sectionService.delete(section);
        return ResponseEntity.status(HttpStatus.OK).body("Seção excluída com sucesso!");
    }
}

package com.projeto.restaurante.controller;

import com.projeto.restaurante.dto.CompanyDto;
import com.projeto.restaurante.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid CompanyDto companyDto){
        CompanyDto retorno = companyService.create(companyDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("api/v1/company").
                buildAndExpand(retorno).toUri();
        return ResponseEntity.created(uri).body("Empresa criada com sucesso!");
    }

    @GetMapping
    public CompanyDto findCompany(){
        return companyService.findCompany();
    }

    @PatchMapping("/{cnpj}")
    public ResponseEntity<Object> update(@PathVariable String cnpj, @RequestBody @Valid CompanyDto companyDto){
        companyService.update(cnpj, companyDto);
        return ResponseEntity.status(HttpStatus.OK).body("Empresa autalizlada com sucesso!");
    }

    @DeleteMapping("/{cnpj}")
    public ResponseEntity<Object> delete(@PathVariable String cnpj){
        companyService.delete(cnpj);
        return ResponseEntity.status(HttpStatus.OK).body("Empresa apagada com sucesso!");
    }
}

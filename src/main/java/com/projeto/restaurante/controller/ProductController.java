package com.projeto.restaurante.controller;

import com.projeto.restaurante.dto.ProductDto;
import com.projeto.restaurante.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid ProductDto productDto){
        ProductDto retorno = service.create(productDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/product").
                buildAndExpand(retorno).toUri();
        return ResponseEntity.created(uri).body("Produto cadastrado com sucesso!");
    }
}

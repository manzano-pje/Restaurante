package com.projeto.restaurante.controller;

import com.projeto.restaurante.dto.ProductDto;
import com.projeto.restaurante.dto.ProductReturnDto;
import com.projeto.restaurante.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid ProductDto productDto){
        ProductReturnDto retorno = service.create(productDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/api/v1/product").
                buildAndExpand(retorno).toUri();
        return ResponseEntity.created(uri).body("Produto cadastrado com sucesso!");
    }

    @GetMapping
    public List<ProductReturnDto> findAll(){
        return service.findAll();
    }
}

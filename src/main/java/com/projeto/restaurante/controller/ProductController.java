package com.projeto.restaurante.controller;

import com.projeto.restaurante.dto.ProductDto;
import com.projeto.restaurante.dto.ProductReturnDto;
import com.projeto.restaurante.service.ProductService;
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

    @GetMapping("/product/{name}")
    public ProductReturnDto findOne(@PathVariable String name){
        return service.findOne(name);
    }

    @GetMapping("/group/{group}")
    public List<ProductReturnDto> findbyGroup(@PathVariable String group){
        return service.findbyGroup(group);
    }

    @PatchMapping("/{name}")
    public ResponseEntity<Object> Update(@PathVariable String name, @RequestBody @Valid ProductDto productDto){
        service.update(name, productDto);
        return ResponseEntity.status(HttpStatus.OK).body("Produto atualizado com sucesso!");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Object> delete(@PathVariable String name){
        service.delete(name);
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluído com sucesso!");
    }
}

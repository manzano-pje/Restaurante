package com.projeto.restaurante.controller;

import com.projeto.restaurante.dto.RequestInputDto;
import com.projeto.restaurante.dto.ReturnRequestDto;
import com.projeto.restaurante.service.RequestService;
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
@RequestMapping("api/v1/request")
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid RequestInputDto requestDto){
        ReturnRequestDto retorno = requestService.create(requestDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("api/v1/request").
                buildAndExpand(retorno).toUri();
        return ResponseEntity.created(uri).body("Pedido criado com sucesso!");

    }
}

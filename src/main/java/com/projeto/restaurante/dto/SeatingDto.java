package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Seating;
import lombok.*;

//@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatingDto {
    private String name;

    public SeatingDto(Seating seating) {
        this.name = seating.getName();
    }
}

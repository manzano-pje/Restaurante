package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Seating;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatingDto {
    private String name;
    private boolean occuped;

    public SeatingDto(Seating seating) {

        this.name = seating.getName();
        this.occuped = seating.isOccuped();
    }
}

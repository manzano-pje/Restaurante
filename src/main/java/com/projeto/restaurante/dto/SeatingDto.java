package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Seating;
import lombok.*;

import java.util.Date;

//@AllArgsConstructor
@NoArgsConstructor
@Data
public class SeatingDto {
    private String name;
    private Date openingDate;
    private Date closingDate;
    private boolean status;

    public SeatingDto(Seating seating) {
        this.name = seating.getName();
        this.openingDate = seating.getOpeningDate();
        this.closingDate = seating.getClosingDate();
        this.status = seating.isStatus();
    }
}

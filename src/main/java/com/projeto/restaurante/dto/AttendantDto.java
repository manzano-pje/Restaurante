package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Attendant;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendantDto {

    private String name;

    public AttendantDto(Attendant attendant) {
        this.name = attendant.getName();
    }
}

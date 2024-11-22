package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Section;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionDto {

    private int id;
    @NonNull
    private String name;

    public SectionDto(Section section) {
        this.id = section.getId();
        this.name = section.getName();
    }
}

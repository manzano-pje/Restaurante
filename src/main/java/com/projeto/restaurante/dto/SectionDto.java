package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Section;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {

    int id;
    @NonNull
    String name;

    public SectionDto(Section section) {
        this.id = section.getId();
        this.name = section.getName();
    }
}

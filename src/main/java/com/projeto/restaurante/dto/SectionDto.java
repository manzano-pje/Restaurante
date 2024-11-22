package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Section;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
public class SectionDto {

    int sectionId;
    @NonNull
    String name;

    public SectionDto(Section section) {
        this.sectionId = section.getSectionId();
        this.name = section.getName();
    }
}

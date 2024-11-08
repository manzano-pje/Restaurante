package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Group;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {

    private String name;

    public GroupDto(Group group) {
        this.name = group.getName();
    }
}

package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Group;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GroupDto {

    private int id;
    private String name;
    private int sectoinId;

    public GroupDto(Group group) {
        this.id = group.getId();
        this.name = group.getName();
        this.sectoinId = group.getSectionGroup().getSectionId();
    }
}

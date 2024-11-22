package com.projeto.restaurante.identities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tb_section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int sectionId;
    @NonNull
    String name;

    /******** RELATIONS ********/

    @OneToMany (mappedBy = "sectionGroup",cascade = CascadeType.ALL)
    private List<Group> groups = new ArrayList<>();
}

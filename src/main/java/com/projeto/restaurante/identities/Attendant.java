package com.projeto.restaurante.identities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_attendant")
public class Attendant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    /******** RELATIONS ********/

    @OneToMany (mappedBy = "requestAttendant",cascade = CascadeType.ALL)
    private List<Request> requests = new ArrayList<>();
}

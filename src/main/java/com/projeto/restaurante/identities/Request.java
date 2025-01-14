package com.projeto.restaurante.identities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "tb_request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private long requestNumber;
    private double total;
    private boolean aberto;

    @Override
    public String toString(){
        return "Request("+
                "id = " + id +
                ", attendant = " + requestAttendant.getName() +
                '}';
    }

    /******** RELATIONS ********/
    @OneToMany (mappedBy = "request",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RequestItem> itens = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendant_id", nullable = false)
    private Attendant requestAttendant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seating_id", nullable = false)
    private Seating requestSeating;
}

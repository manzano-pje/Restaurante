package com.projeto.restaurante.identities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private long requestNumber;
    private double total;
    private Date openingDate;
    private Date closingDate;
    private boolean status;

    /******** RELATIONS ********/
    @OneToMany (mappedBy = "request",cascade = CascadeType.ALL)
    private List<RequestItem> itens = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attendant_id", nullable = false)
    private Attendant requestAttendant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seating_id", nullable = false)
    private Seating requestSeating;
}

package com.projeto.restaurante.identities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @Override
    public String toString(){
        return "Request("+
                "id = " + id +
                ", openingDate = " + openingDate +
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

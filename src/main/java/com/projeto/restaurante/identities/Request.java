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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int requestNumber;
    private int attendantId;
    private int productId;
    private int seatingId;
    private int quantity;
    private double total;
    private double subtotal;
    private Date openingDate;
    private Date closingDate;
    private boolean free;

    /******** RELATIONS ********/

    @OneToMany (mappedBy = "productRequest",cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attendant_id", nullable = false)
    private Attendant requestAttendant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seating_id", nullable = false)
    private Seating requestSeating;






}

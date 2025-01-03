package com.projeto.restaurante.identities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_seating")
public class Seating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String name;
    private Date openingDate;
    private Date closingDate;
    private boolean status;

    public Seating(Seating seating) {
        this.name = seating.getName();
        this.openingDate = seating.getOpeningDate();
        this.closingDate = seating.getClosingDate();
        this.status = seating.status;
    }

    @Override
    public String toString(){
        return "Seating("+
                "id = " + id +
                ", name = " + name +
                ", openingDate = " + openingDate  +
                ", closingDate = " + closingDate +
                ", status = " + status +
                '}';
    }

    /******** RELATIONS ********/

    @OneToMany (mappedBy = "requestSeating",cascade = CascadeType.ALL)
    private List<Request> requests = new ArrayList<>();
}

package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RetrunRequest {

    @NonNull
    private int requestNumber;
    @NonNull
    private int attendantId;
    @NonNull
    private int productId;
    @NonNull
    private int seatingId;
    @NonNull
    private int quantity;
    @NonNull
    private double total;
    @NonNull
    private double subtotal;
    @NonNull
    private Date openingDate;
    @NonNull
    private Date closingDate;
    @NonNull
    private boolean free;

    public RetrunRequest(Request request){
        this.requestNumber = request.getRequestNumber();
        this.attendantId = request.getAttendantId();
        this.productId = request.getProductId();
        this.seatingId = request.getSeatingId();
        this.quantity = request.getQuantity();
        this.total = request.getTotal();
        this.subtotal = request.getSubtotal();
        this.openingDate = request.getOpeningDate();
        this.closingDate = request.getClosingDate();
    }
}

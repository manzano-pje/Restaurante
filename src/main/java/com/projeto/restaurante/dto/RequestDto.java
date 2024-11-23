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
public class RequestDto {

    @NonNull
    private int seatingId;
    @NonNull
    private int attendantId;
    @NonNull
    private int productId;
    @NonNull
    private int quantity;

    public RequestDto(Request request){
        this.seatingId = request.getSeatingId();
        this.attendantId = request.getAttendantId();
        this.productId = request.getProductId();
        this.quantity = request.getQuantity();
    }
}

package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Request;
import com.projeto.restaurante.identities.RequestItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnRequestDto {

    @NonNull
    private long requestNumber;
    @NonNull
    private int attendantId;
    @NonNull
    private int seatingId;
    @NonNull
    private double total;
    private Date openingDate;
    private Date closingDate;
    @NonNull
    private boolean status;
    @NonNull
    private List<RequestItemDto> itens = new ArrayList<>();

    public ReturnRequestDto(Request request){
        this.requestNumber = request.getRequestNumber();
        this.attendantId = request.getRequestAttendant().getId();
        this.seatingId = request.getRequestSeating().getId();
        this.total = request.getTotal();
        this.status = request.isStatus();

        for(RequestItem item : request.getItens()){
            this.itens.add(new RequestItemDto(item));
        }
    }
}

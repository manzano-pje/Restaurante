package com.projeto.restaurante.dto;

import com.projeto.restaurante.identities.Request;
import com.projeto.restaurante.identities.RequestItem;
import com.projeto.restaurante.identities.Seating;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReturnRequestDto {

    private String seatingName;
    private String attendantName;
    private String openingDate;
    private double total;
    private List<RequestItemDto> itens = new ArrayList<>();

//    public ReturnRequestDto(Seating seating, List<Request> requests){
//        this.seatingName = seating.getName();
//        this.openingDate = requests.get(0).getOpeningDate() != null ?
//                new SimpleDateFormat("dd/MM/yyyy - HH:mm").
//                        format(requests.get(0).getOpeningDate()):null;
//        double totalSum = 0;
//        int itemNumber = 1;
//
//        for(Request request : requests){
//            for(RequestItem item : request.getItens()) {
//                this.itens.add(new RequestItemDto(item, itemNumber++));
//                totalSum += item.getSubtotal();
//            }
//        }
//    }
}

package com.projeto.restaurante.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClosingSeatingDto {

    private String seatingName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm")
    private Date openingDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm")
    private Date closingDate;
    private String total;
    private List<RequestItemDto> itens;

    public ClosingSeatingDto(String seatingName, double total, Date openingDate, Date closingDate, List<RequestItemDto> itens){
        this.seatingName = seatingName;
        this.total = formatCurrency(total);
        this.openingDate = openingDate;
        this.closingDate = closingDate;
        this.itens = itens;
    }

    private String formatCurrency(double value){
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatter.format(value);
    }
}
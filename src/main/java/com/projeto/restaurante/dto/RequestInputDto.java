package com.projeto.restaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestInputDto {


    @NonNull
    private int seatingId;
    @NonNull
    private int attendantId;
    @NonNull
    private List<RequestItemInputDto> itens;
}

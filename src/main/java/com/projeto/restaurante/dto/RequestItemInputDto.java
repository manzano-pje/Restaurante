package com.projeto.restaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestItemInputDto {

    @NonNull
    private int productId;
    @NonNull
    private int quantity;
}

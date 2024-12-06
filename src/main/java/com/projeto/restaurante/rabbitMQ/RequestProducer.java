package com.projeto.restaurante.rabbitMQ;

import com.projeto.restaurante.configuration.RabbitMQConfig;
import com.projeto.restaurante.dto.RequestMessageDto;
import com.projeto.restaurante.identities.Request;
import com.projeto.restaurante.identities.RequestItem;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToSection(Request request) {


        for(RequestItem item : request.getItens()){

            // Separação por seção com base no grupo do produto
            String section = item.getProduct().getProductGroup().getGroupSection().getName();
            String routingKey = determineRoutingKey(section);

            // criação da mensagem

            RequestMessageDto message = new RequestMessageDto(
                    request.getRequestSeating().getId(), // Número da mesa
                    request.getRequestNumber(),          // Número do pedido
                    item.getProduct().getNameProduct(),  // Nome do produto
                    item.getQuantity()                  // Quantidade
//                            .section                              // Seção
            );

            // envio da mensagem para a fila apropriada
            rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE_SECTIONS,
                    routingKey,
                    message
            );
            System.out.println("Mensagem enviada para a seção " + section + ": " + message);
        }
    }

    private String determineRoutingKey(String section){
        switch (section.toLowerCase()){
            case "cozinha":
                return "kitchen";
            case "bar":
                return"bar";
            case "balcão":
                return "blank";
            default:
                throw new IllegalArgumentException("Seção desconhecida: " + section);
        }
    }
}
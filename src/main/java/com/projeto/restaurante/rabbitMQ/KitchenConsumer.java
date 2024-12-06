package com.projeto.restaurante.rabbitMQ;

import com.projeto.restaurante.configuration.RabbitMQConfig;
import com.projeto.restaurante.dto.RequestMessageDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class KitchenConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_KITCHEN, containerFactory = "rabbitListenerContainerFactory")
    public void proccessKitchenOrder(RequestMessageDto message){
        System.out.println("\n\n########################\n " +
                           "Pedido recebido na cozinha!\n " +
                           "########################\n " + message +"\n");
    }
}

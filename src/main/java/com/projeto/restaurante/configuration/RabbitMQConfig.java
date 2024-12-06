package com.projeto.restaurante.configuration;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Filas para cada seção
    public static final String QUEUE_KITCHEN = "kitchen_queue";
    public static final String QUEUE_BAR = "bar_queue";
    public static final String QUEUE_BLANK = "blank_queue";

    // Exchange para rotear as mensagens
    public static final String EXCHANGE_SECTIONS = "sections_exchange";


    @Bean
    public Jackson2JsonMessageConverter JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public DirectExchange sectionsExchange() {
        return new DirectExchange(EXCHANGE_SECTIONS);
    }

    @Bean
    public Queue kitchenQueue() {
        return new Queue(QUEUE_KITCHEN,true);
    }

    @Bean
    public Queue barQueue() {
        return new Queue(QUEUE_BAR,true);
    }

    @Bean
    public Queue blankQueue() {
        return new Queue(QUEUE_BLANK,true);
    }


    @Bean
    public Binding kitchenBinding(Queue kitchenQueue, DirectExchange sectionsExchange) {
        return BindingBuilder.bind(kitchenQueue).to(sectionsExchange).with("kitchen");
    }

    @Bean
    public Binding barBinding(Queue barQueue, DirectExchange sectionsExchange) {
        return BindingBuilder.bind(barQueue).to(sectionsExchange).with("bar");
    }

    @Bean
    public Binding BlankBinding(Queue blankQueue, DirectExchange sectionsExchange) {
        return BindingBuilder.bind(blankQueue).to(sectionsExchange).with("blank");
    }
}

package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.MessagingMessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange.name}")
    private String EXCHANGE_NAME;

    @Value("${rabbitmq.queue.string.name}")
    private String QUEUE_STRING_NAME;

    @Value("${rabbitmq.routing.string.key}")
    private String ROUTING_KEY_STRING;

    @Value("${rabbitmq.queue.json.name}")
    private String QUEUE_JSON_NAME;

    @Value("${rabbitmq.routing.json.key}")
    private String ROUTING_KEY_JSON;

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_STRING_NAME, true);
    }

    @Bean
    public Queue jsonQueue() {
        return new Queue(QUEUE_JSON_NAME, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(ROUTING_KEY_STRING);
    }

    @Bean
    public Binding jsonBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(ROUTING_KEY_JSON);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}

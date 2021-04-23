package org.js.azdanov.notificationservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig {
    public static final String DEPOSIT_QUEUE = "deposit.notify";
    private static final String DEPOSIT_TOPIC_EXCHANGE = "deposit.notify.exchange";
    private static final String DEPOSIT_ROUTING_KEY = "deposit.key";

    @Bean
    public TopicExchange depositExchange() {
        return new TopicExchange(DEPOSIT_TOPIC_EXCHANGE);
    }

    @Bean
    public Queue depositQueue() {
        return new Queue(DEPOSIT_QUEUE);
    }

    @Bean
    public Binding depositBinding() {
        return BindingBuilder
                .bind(depositQueue())
                .to(depositExchange())
                .with(DEPOSIT_ROUTING_KEY);
    }
}

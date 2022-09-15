package com.wordprocessor.worker;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Bean
    public Queue q() {
        return new Queue("q");
    }

    @Bean
    public Receiver receiver() {
        return new Receiver();
    }

}

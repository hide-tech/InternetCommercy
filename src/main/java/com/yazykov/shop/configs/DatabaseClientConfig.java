package com.yazykov.shop.configs;

import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;

@Configuration
@RequiredArgsConstructor
public class DatabaseClientConfig {

    private final ConnectionFactory connectionFactory;

    @Bean
    public DatabaseClient databaseClient(){
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .namedParameters(true)
                .build();
    }
}

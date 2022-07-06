package com.yazykov.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;

@EnableReactiveMongoRepositories
@EnableReactiveMethodSecurity
@SpringBootApplication
@EnableR2dbcRepositories
public class InternetCommercyApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternetCommercyApplication.class, args);
	}

}

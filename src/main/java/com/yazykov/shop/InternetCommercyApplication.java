package com.yazykov.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;

@EnableReactiveMongoRepositories
@EnableReactiveMethodSecurity
@SpringBootApplication
public class InternetCommercyApplication {

	public static void main(String[] args) {
		SpringApplication.run(InternetCommercyApplication.class, args);
	}

}

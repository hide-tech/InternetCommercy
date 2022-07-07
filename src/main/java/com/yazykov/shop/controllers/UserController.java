package com.yazykov.shop.controllers;

import com.yazykov.shop.dto.Login;
import com.yazykov.shop.jwt.JwtSupport;
import com.yazykov.shop.jwt.utils.BearerToken;
import com.yazykov.shop.services.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {

    private final CustomerService service;
    private final PasswordEncoder encoder;
    private final JwtSupport jwtSupport;

    @PostMapping("/login")
    public Mono<BearerToken> login(@RequestBody Login login){

        return service.findByUsername(login.getUsername()).filter(userDetails -> encoder
                .matches(login.getPassword(), userDetails.getPassword()))
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED)))
                .flatMap(userDetails -> Mono.just(jwtSupport.generateToken(userDetails.getUsername())));

    }
}

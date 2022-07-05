package com.yazykov.shop.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ReactiveUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveAuthenticationManager reactiveAuthenticationManager(){
        return authentication -> {
            UserDetailsRepositoryReactiveAuthenticationManager manager =
                    new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
            manager.setPasswordEncoder(passwordEncoder());

            return manager.authenticate(authentication);
        };
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        http
                .authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/login").permitAll()
                .anyExchange().authenticated()
                .and()
                .httpBasic().disable()
                .formLogin().disable()
                .csrf().disable();

        return http.build();
    }

}

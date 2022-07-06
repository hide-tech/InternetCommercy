package com.yazykov.shop.jwt;

import com.yazykov.shop.jwt.utils.BearerToken;
import com.yazykov.shop.jwt.utils.InvalidBearerToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtSupport jwtSupport;
    private final ReactiveUserDetailsService customerService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.justOrEmpty(authentication)
                .filter(auth -> auth instanceof BearerToken)
                .cast(BearerToken.class)
                .flatMap(jwt -> Mono.just(validate(jwt)))
                .onErrorMap(error -> new InvalidBearerToken(error.getMessage()));
    }

    private Authentication validate(BearerToken token){
        String username = jwtSupport.getUsernameFromToken(token);
        UserDetails user = customerService.findByUsername(username).block();

        if (user != null && jwtSupport.isValidToken(token, user)){
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        }

        throw new IllegalArgumentException("Token isn't valid");
    }
}



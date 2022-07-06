package com.yazykov.shop.jwt;

import com.yazykov.shop.jwt.utils.BearerToken;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtServerAuthenticationConverter implements ServerAuthenticationConverter {

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION))
                .filter(it -> it.startsWith("Bearer "))
                .map(str -> str.substring(7))
                .map(BearerToken::new);
    }
}

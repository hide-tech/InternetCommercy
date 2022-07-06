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
        final UserDetails[] user = new UserDetails[1];

        customerService.findByUsername(username).subscribe(val -> user[0] = val);

        if (user[0] != null && jwtSupport.isValidToken(token, user[0])){
            return new UsernamePasswordAuthenticationToken(
                    user[0].getUsername(),
                    user[0].getPassword(),
                    user[0].getAuthorities());
        }

        throw new IllegalArgumentException("Token isn't valid");
    }
}



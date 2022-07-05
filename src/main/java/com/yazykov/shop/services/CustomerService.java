package com.yazykov.shop.services;

import com.yazykov.shop.model.CurrentUser;
import com.yazykov.shop.repositories.CustomerRepository;
import com.yazykov.shop.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService implements ReactiveUserDetailsService {

    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Mono<CurrentUser> currentUser = customerRepository.findByUsername(username);

        return currentUser.flatMap(user -> {
            return Mono.just(User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .authorities(Objects.requireNonNull(roleRepository.findRolesByUsername(username).collectList()
                            .block()).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
                    .build());
        });
    }
}

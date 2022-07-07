package com.yazykov.shop.services;

import com.yazykov.shop.model.CurrentUser;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class CustomerService implements ReactiveUserDetailsService {

    private final DatabaseClient databaseClient;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Mono<CurrentUser> currentUser = findCustomerByUsername(username);
        List<String> roles = new LinkedList<>();

        return currentUser.flatMap(user -> {
            return Mono.just(User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build());
        });
    }

    private Mono<CurrentUser> findCustomerByUsername(String username){
        return databaseClient
                .sql("select c.id, c.username, c.password, c.role from customers c where c.username = :username")
                .bind("username", username)
                .map(MAPPING_FUNCTION_CUSTOMER)
                .one();
    }

    public static final BiFunction<Row, RowMetadata, CurrentUser> MAPPING_FUNCTION_CUSTOMER = (row, rowMetaData) ->
            new CurrentUser(
                    row.get("id", Long.class),
                    row.get("username", String.class),
                    row.get("password", String.class),
                    row.get("role", String.class));
}

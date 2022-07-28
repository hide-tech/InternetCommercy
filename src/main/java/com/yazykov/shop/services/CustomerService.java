package com.yazykov.shop.services;

import com.yazykov.shop.dto.CustomerDto;
import com.yazykov.shop.dto.NewCustomerAccount;
import com.yazykov.shop.dto.errors.UserNotFoundException;
import com.yazykov.shop.mappers.CustomerMapper;
import com.yazykov.shop.model.CurrentUser;
import com.yazykov.shop.model.Customer;
import com.yazykov.shop.repositories.CustomerRepository;
import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class CustomerService implements ReactiveUserDetailsService {

    private final DatabaseClient databaseClient;
    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        Mono<CurrentUser> currentUser = findCustomerByUsername(username);

        return currentUser.flatMap(user -> {
            return Mono.just(User.withUsername(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build());
        });
    }

    public Flux<CustomerDto> createUser(Mono<NewCustomerAccount> customerAccountMono){
        return repository.saveAll(customerAccountMono.map(Customer::new)
                        .doOnNext(c -> c.setPassword(new BCryptPasswordEncoder().encode(c.getPassword()))))
                .map(mapper::customerToCustomerDto);
    }

    private Flux<String> findRolesByUsername(String username){
        return databaseClient
                .sql("select r.name from customers c left join user_roles ur on c.id=ur.customer.id left join " +
                        "roles r on ur.role_id=r.id where c.username=:username")
                .bind("username", username)
                .map(MAPPING_FUNCTION_ROLE)
                .all();
    }

    public static final BiFunction<Row, RowMetadata, String> MAPPING_FUNCTION_ROLE = (row, rowMetaData) ->
            String.valueOf(row.get("r.name", String.class));

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

    public Flux<CustomerDto> findAllUsers() {
        return repository.findAll().map(mapper::customerToCustomerDto);
    }

    public Mono<CustomerDto> findUserById(Long id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new UserNotFoundException("User not exist with id: "+id)))
                .map(mapper::customerToCustomerDto);
    }

    public Mono<Customer> enterIntoUserAccount(String username) {
        return repository.findByUsername(username);
    }

    public Mono<CustomerDto> banCustomer(Long customerId){
        return repository.findById(customerId)
                .doOnNext(customer -> customer.setLocked(true))
                .flatMap(repository::save)
                .map(mapper::customerToCustomerDto);
    }

    public Mono<CustomerDto> unbanCustomer(Long customerId){
        return repository.findById(customerId)
                .doOnNext(customer -> customer.setLocked(false))
                .flatMap(repository::save)
                .map(mapper::customerToCustomerDto);
    }
}

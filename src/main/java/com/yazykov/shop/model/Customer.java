package com.yazykov.shop.model;

import com.yazykov.shop.dto.NewCustomerAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("customers")
public class Customer implements UserDetails {
    @Id
    private Long id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String surname;
    private Role role;
    private boolean expired;
    private boolean locked;
    private boolean enable;

    public Customer(NewCustomerAccount account){
        username = account.getUsername();
        email = account.getEmail();
        password = account.getPassword();
        name = account.getName();
        surname = account.getSurname();
        role = Role.Role_USER;
        expired = false;
        locked = false;
        enable = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !expired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}

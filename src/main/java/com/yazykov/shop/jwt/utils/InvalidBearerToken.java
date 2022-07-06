package com.yazykov.shop.jwt.utils;

import lombok.Getter;

import javax.naming.AuthenticationException;

@Getter
public class InvalidBearerToken extends AuthenticationException {

    public InvalidBearerToken(String message){
        super(message);
    }
}

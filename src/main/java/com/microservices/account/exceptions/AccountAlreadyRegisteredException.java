package com.microservices.account.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountAlreadyRegisteredException extends RuntimeException {
    @Builder
    public AccountAlreadyRegisteredException() {
        super("Account already registered!");
    }
}
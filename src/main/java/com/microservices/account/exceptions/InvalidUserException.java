package com.microservices.account.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserException extends RuntimeException {
    @Builder
    public InvalidUserException() {
        super("Invalid user. Please check your log in and password and try again!");
    }
}
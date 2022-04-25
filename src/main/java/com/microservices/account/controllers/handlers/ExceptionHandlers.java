package com.microservices.account.controllers.handlers;

import com.microservices.account.exceptions.AccountAlreadyRegisteredException;
import com.microservices.account.exceptions.AccountNotFoundException;
import com.microservices.account.exceptions.InvalidUserException;
import com.microservices.user.payloads.errors.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlers {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errorMessages = exception.getFieldErrors().stream().map(this::mapToErrorMessage).collect(Collectors.toList());
        ErrorResponse errorResponse = ErrorResponse.builder().errorMessages(errorMessages).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(InvalidUserException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder().errorMessages(List.of(exception.getMessage())).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserAlreadyRegisteredException(AccountNotFoundException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder().errorMessages(List.of(exception.getMessage())).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAccountAlreadyRegisteredException(AccountAlreadyRegisteredException exception) {
        ErrorResponse errorResponse = ErrorResponse.builder().errorMessages(List.of(exception.getMessage())).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
    
    private String mapToErrorMessage(FieldError fieldError) {
        return fieldError.getField() + ":" + fieldError.getDefaultMessage();
    }
}

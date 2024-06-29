package com.example.demo.core.domain.exception;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(final String message){
        super(message);
    };

    public ClienteNotFoundException(final String message, final Throwable cause){
        super(message, cause);
    };
}

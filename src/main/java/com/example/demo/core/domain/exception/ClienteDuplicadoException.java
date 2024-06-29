package com.example.demo.core.domain.exception;

public class ClienteDuplicadoException extends RuntimeException {

    public ClienteDuplicadoException(final String message){
        super(message);
    };

    public ClienteDuplicadoException(final String message, final Throwable cause){
        super(message, cause);
    };
}

package com.example.demo.core.domain.exception;

public class PedidoNotFoundException extends RuntimeException {

    public PedidoNotFoundException(final String message){
        super(message);
    };

    public PedidoNotFoundException(final String message, final Throwable cause){
        super(message, cause);
    };
}

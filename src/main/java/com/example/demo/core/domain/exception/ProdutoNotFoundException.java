package com.example.demo.core.domain.exception;

public class ProdutoNotFoundException extends RuntimeException {

    public ProdutoNotFoundException(final String message){
        super(message);
    };

    public ProdutoNotFoundException(final String message, final Throwable cause){
        super(message, cause);
    };
}

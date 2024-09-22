package com.example.demo.core.usecase.impl;

import com.example.demo.adapter.gateway.interfaces.cliente.IncluirClienteAdapterPort;
import com.example.demo.adapter.gateway.interfaces.cliente.RecuperarClienteAdapterPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class IncluirClienteUseCaseTest {

    @Mock
    private IncluirClienteAdapterPort incluirClienteAdapterPort;

    @Mock
    private RecuperarClienteAdapterPort recuperarClienteAdapterPort;

    @InjectMocks
    private IncluirClienteUseCase incluirClienteUseCase;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }




}
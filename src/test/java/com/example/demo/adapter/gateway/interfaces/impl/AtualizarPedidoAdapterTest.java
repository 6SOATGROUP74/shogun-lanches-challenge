package com.example.demo.adapter.gateway.interfaces.impl;

import com.example.demo.adapter.gateway.interfaces.pedido.AtualizarPedidoAdapterPort;
import com.example.demo.infrastructure.repository.PedidoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
class AtualizarPedidoAdapterTest {


    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private AtualizarPedidoAdapter adapter;

    @BeforeAll
    public static void setup(){

    }

    @Test
    void execute() {


    }


}
package com.example.demo.adapter.gateway.interfaces.impl;

import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.domain.Pedido;
import com.example.demo.infrastructure.repository.PagamentoRepository;
import com.example.demo.infrastructure.repository.PedidoRepository;
import com.example.demo.infrastructure.repository.entity.PagamentoEntity;
import com.example.demo.infrastructure.repository.entity.PedidoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SalvarPedidoAdapterTest {

    @Mock
    private PedidoRepository repository;

    @InjectMocks
    private SalvarPedidoAdapter salvarPedidoAdapter;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_NaoDeveRetornarException() {
        when(repository.save(any(PedidoEntity.class)))
                .thenReturn(new PedidoEntity());

        assertDoesNotThrow(() -> salvarPedidoAdapter.execute(any(Pedido.class)));
    }
}
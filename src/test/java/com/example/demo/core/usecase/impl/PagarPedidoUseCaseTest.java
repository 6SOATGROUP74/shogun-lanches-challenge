package com.example.demo.core.usecase.impl;

import com.example.demo.adapter.gateway.interfaces.pagamento.SalvarPagamentoAdapterPort;
import com.example.demo.adapter.gateway.interfaces.pedido.AtualizarPedidoAdapterPort;
import com.example.demo.adapter.gateway.interfaces.pedido.BuscarPedidoAdapterPort;
import com.example.demo.core.domain.Pagamento;
import com.example.demo.exceptions.PedidoNotFoundException;
import com.example.demo.infrastructure.integration.pagbank.PagarPedidoPagbankAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class PagarPedidoUseCaseTest {

    @Mock
    private PagarPedidoPagbankAdapter pagarPedidoPagbankAdapter;

    @Mock
    private SalvarPagamentoAdapterPort salvarPagamentoAdapterPort;

    @Mock
    private BuscarPedidoAdapterPort buscarPedidoAdapterPort;

    @Mock
    private AtualizarPedidoAdapterPort atualizarPedidoAdapterPort;

    @InjectMocks
    PagarPedidoUseCase pagarPedidoUseCase;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void checkout_DeveLancarExceptionPedidoNotFoundException(){

        final var pagamento = new Pagamento();

        when(buscarPedidoAdapterPort.execute(eq(buildPagamento().getNumeroPedido()))).thenReturn(null);

        assertThrows(PedidoNotFoundException.class, () -> pagarPedidoUseCase.checkout(buildPagamento()));


    }

    Pagamento buildPagamento(){
        Pagamento pagamento = new Pagamento();
        pagamento.setNumeroPedido(1L);

        return pagamento;
    }

}
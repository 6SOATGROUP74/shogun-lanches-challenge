package com.example.demo.core.usecase.impl;

import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.domain.Pedido;
import com.example.demo.exceptions.PedidoNotFoundException;
import com.example.demo.core.usecase.interfaces.pagamento.PagarPedidoUseCasePort;
import com.example.demo.adapter.gateway.interfaces.pagamento.SalvarPagamentoAdapterPort;
import com.example.demo.adapter.gateway.interfaces.pedido.AtualizarPedidoAdapterPort;
import com.example.demo.adapter.gateway.interfaces.pedido.BuscarPedidoAdapterPort;
import com.example.demo.infrastructure.integration.pagbank.PagarPedidoPagbankAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Objects;

public class PagarPedidoUseCase implements PagarPedidoUseCasePort {

    private final PagarPedidoPagbankAdapter pagarPedidoPagbankAdapter;
    private final SalvarPagamentoAdapterPort salvarPagamentoAdapterPort;
    private final BuscarPedidoAdapterPort buscarPedidoAdapterPort;
    private final AtualizarPedidoAdapterPort atualizarPedidoAdapterPort;

    public PagarPedidoUseCase(PagarPedidoPagbankAdapter pagarPedidoPagbankAdapter, SalvarPagamentoAdapterPort salvarPagamentoAdapterPort, BuscarPedidoAdapterPort buscarPedidoAdapterPort, AtualizarPedidoAdapterPort atualizarPedidoAdapterPort) {
        this.pagarPedidoPagbankAdapter = pagarPedidoPagbankAdapter;
        this.salvarPagamentoAdapterPort = salvarPagamentoAdapterPort;
        this.buscarPedidoAdapterPort = buscarPedidoAdapterPort;
        this.atualizarPedidoAdapterPort = atualizarPedidoAdapterPort;
    }

    @Override
    public Pagamento checkout(Pagamento pagamento) {

        logger.info("m=checkout, status=init,  msg=Iniciando processo de checkout, pagamento={}", pagamento);

        Pedido pedido = buscarPedidoAdapterPort.execute(pagamento.getNumeroPedido());

        if(Objects.isNull(pedido)){
            logger.error("m=checkout, status=failure, msg=Pedido não encontrado para o pagamento={}", pagamento);
            throw new PedidoNotFoundException("Pedido nao localizado.");
        }

        pagamento.setPedido(pedido);
        pagamento.setValorTotal(BigDecimal.valueOf(pedido.getValorTotal()));
        final Pagamento pagamentoProcessado = pagarPedidoPagbankAdapter.pagar(pagamento);

        pagamentoProcessado.setNumeroPedido(pedido.getNumeroPedido());
        pagamentoProcessado.setPedido(pedido);

        final var dadosPagamento = salvarPagamentoAdapterPort.salvar(pagamentoProcessado);

        pedido.setIdPagamento(dadosPagamento.getIdPagamento());

        atualizarPedidoAdapterPort.execute(pedido);

        logger.info("m=checkout, status=succes,  msg=Checkout realizado com sucesso, pagamento={}, pedido={}", pagamentoProcessado, pedido);

        return pagamento;
    }

    private Logger logger = LoggerFactory.getLogger(PagarPedidoUseCase.class);
}

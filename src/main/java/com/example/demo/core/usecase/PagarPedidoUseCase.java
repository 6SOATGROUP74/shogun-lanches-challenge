package com.example.demo.core.usecase;

import com.example.demo.adapter.outbound.integration.pagbank.PagarPedidoPagbankAdapter;
import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.domain.exception.PedidoNotFoundException;
import com.example.demo.core.ports.inbound.pagamento.PagarPedidoUseCasePort;
import com.example.demo.core.ports.outbound.pagamento.SalvarPagamentoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.AtualizarPedidoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.BuscarPedidoAdapterPort;

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
    public void checkout(Pagamento pagamento) {

        Pedido pedido = buscarPedidoAdapterPort.execute(pagamento.getNumeroPedido());

        if(Objects.isNull(pedido)){
            throw new PedidoNotFoundException("Pedido nao localizado.");
        }

        pagamento.setPedido(pedido);
        pagamento.setValorTotal(BigDecimal.valueOf(pedido.getValorTotal()));
        final Pagamento pagamentoProcessado = pagarPedidoPagbankAdapter.pagar(pagamento);

        pedido.setEtapa("EM_PREPARACAO");
        pagamentoProcessado.setNumeroPedido(pedido.getNumeroPedido());
        pagamentoProcessado.setPedido(pedido);

        final var dadosPagamento = salvarPagamentoAdapterPort.salvar(pagamentoProcessado);

        pedido.setIdPagamento(dadosPagamento.getIdPagamento());

        atualizarPedidoAdapterPort.execute(pedido);

    }
}

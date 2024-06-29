package com.example.demo.core.usecase;

import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.domain.exception.PedidoNotFoundException;
import com.example.demo.core.ports.inbound.pagamento.PagarPedidoUseCasePort;
import com.example.demo.core.ports.outbound.pagamento.PagarPedidoAdapterPort;
import com.example.demo.core.ports.outbound.pagamento.SalvarPagamentoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.AtualizarPedidoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.BuscarPedidoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.PedidoEmPreparacaoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.SalvarPedidoAdapterPort;

import java.math.BigDecimal;
import java.util.Objects;

public class PagarPedidoUseCase implements PagarPedidoUseCasePort {

    private final PagarPedidoAdapterPort pagarPedidoAdapterPort;
    private final SalvarPagamentoAdapterPort salvarPagamentoAdapterPort;
    private final BuscarPedidoAdapterPort buscarPedidoAdapterPort;
    private final AtualizarPedidoAdapterPort atualizarPedidoAdapterPort;

    public PagarPedidoUseCase(PagarPedidoAdapterPort pagarPedidoAdapterPort, SalvarPagamentoAdapterPort salvarPagamentoAdapterPort, BuscarPedidoAdapterPort buscarPedidoAdapterPort, AtualizarPedidoAdapterPort atualizarPedidoAdapterPort) {
        this.pagarPedidoAdapterPort = pagarPedidoAdapterPort;
        this.salvarPagamentoAdapterPort = salvarPagamentoAdapterPort;
        this.buscarPedidoAdapterPort = buscarPedidoAdapterPort;
        this.atualizarPedidoAdapterPort = atualizarPedidoAdapterPort;
    }

    @Override
    public void checkout(Pagamento pagamento) {

        final Pagamento pago = pagarPedidoAdapterPort.pagar(pagamento);
        Pedido pedido = buscarPedidoAdapterPort.execute(pagamento.getNumeroPedido());

        if(Objects.isNull(pedido)){
            throw new PedidoNotFoundException("Pedido nao localizado.");
        }

        pedido.setEtapa("EM_PREPARACAO");
        pago.setNumeroPedido(pedido.getNumeroPedido());
        pago.setValorTotal(BigDecimal.valueOf(pedido.getValorTotal()));

        pago.setPedido(pedido);
        final var dadosPagamento = salvarPagamentoAdapterPort.salvar(pago);

        pedido.setIdPagamento(dadosPagamento.getIdPagamento());

        atualizarPedidoAdapterPort.execute(pedido);

    }
}

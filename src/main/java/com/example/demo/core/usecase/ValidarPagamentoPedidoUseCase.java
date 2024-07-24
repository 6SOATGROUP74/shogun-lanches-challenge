package com.example.demo.core.usecase;

import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.domain.exception.PedidoNotFoundException;
import com.example.demo.core.ports.inbound.pagamento.ValidarPagamentoPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.ListarPedidosUseCasePort;
import com.example.demo.core.ports.outbound.pagamento.SalvarPagamentoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.AtualizarPedidoAdapterPort;

import java.math.BigDecimal;
import java.util.Objects;

public class ValidarPagamentoPedidoUseCase implements ValidarPagamentoPedidoUseCasePort {

    private final ListarPedidosUseCasePort listarPedidosUseCasePort;
    private final SalvarPagamentoAdapterPort salvarPagamentoAdapterPort;
    private final AtualizarPedidoAdapterPort atualizarPedidoAdapterPort;

    public ValidarPagamentoPedidoUseCase(ListarPedidosUseCasePort listarPedidosUseCasePort, SalvarPagamentoAdapterPort salvarPagamentoAdapterPort, AtualizarPedidoAdapterPort atualizarPedidoAdapterPort) {
        this.listarPedidosUseCasePort = listarPedidosUseCasePort;
        this.salvarPagamentoAdapterPort = salvarPagamentoAdapterPort;
        this.atualizarPedidoAdapterPort = atualizarPedidoAdapterPort;
    }

    @Override
    public Pagamento execute(Pagamento pagamento) {
        Pedido pedido = listarPedidosUseCasePort.listarPorCodReferencia(pagamento.getPedido().getCodReferenciaPedido());

        if(Objects.isNull(pedido)){
            throw new PedidoNotFoundException("Pedido nao localizado.");
        }

        pagamento.setPedido(pedido);
        pagamento.setValorTotal(BigDecimal.valueOf(pedido.getValorTotal()));
        pagamento.setCodPagamento(pagamento.getCodPagamento());

        pedido.setEtapa("EM_PREPARACAO");
        pagamento.setNumeroPedido(pedido.getNumeroPedido());
        pagamento.setPedido(pedido);

        final var dadosPagamento = salvarPagamentoAdapterPort.salvar(pagamento);

        pedido.setIdPagamento(dadosPagamento.getIdPagamento());

        atualizarPedidoAdapterPort.execute(pedido);

        return pagamento;
    }
}

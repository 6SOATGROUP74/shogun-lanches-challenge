package com.example.demo.core.usecase;

import com.example.demo.adapter.outbound.integration.pagbank.ProcessaStatusPagamentoPagbankAdapter;
import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.domain.exception.PagamentoNotFoundException;
import com.example.demo.core.ports.inbound.pagamento.AlterarStatusPagamentoUseCasePort;
import com.example.demo.core.ports.outbound.pagamento.BuscarPagamentoAdapterPort;
import com.example.demo.core.ports.outbound.pagamento.SalvarPagamentoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.BuscarPedidoAdapterPort;

import java.util.Objects;

public class AlterarStatusPagamentoUseCase implements AlterarStatusPagamentoUseCasePort {

    private final ProcessaStatusPagamentoPagbankAdapter processaStatusPagamentoPagbankAdapter;
    private final BuscarPagamentoAdapterPort buscarPagamentoAdapterPort;
    private final SalvarPagamentoAdapterPort salvarPagamentoAdapterPort;
    private final BuscarPedidoAdapterPort buscarPedidoAdapterPort;

    public AlterarStatusPagamentoUseCase(ProcessaStatusPagamentoPagbankAdapter processaStatusPagamentoPagbankAdapter, BuscarPagamentoAdapterPort buscarPagamentoAdapterPort, SalvarPagamentoAdapterPort salvarPagamentoAdapterPort, BuscarPedidoAdapterPort buscarPedidoAdapterPort) {
        this.processaStatusPagamentoPagbankAdapter = processaStatusPagamentoPagbankAdapter;
        this.buscarPagamentoAdapterPort = buscarPagamentoAdapterPort;
        this.salvarPagamentoAdapterPort = salvarPagamentoAdapterPort;
        this.buscarPedidoAdapterPort = buscarPedidoAdapterPort;
    }

    @Override
    public Pagamento execute(Long pagamentoId) {
        Pagamento pagamentoAtual = buscarPagamentoAdapterPort.buscar(pagamentoId);

        if(Objects.isNull(pagamentoAtual)){
            throw new PagamentoNotFoundException("Pagamento não encontrado");
        }

        Pedido pedidoAtual = buscarPedidoAdapterPort.execute(pagamentoAtual.getNumeroPedido());
        pagamentoAtual.setPedido(pedidoAtual);

        final Pagamento pagamentoProcessado = processaStatusPagamentoPagbankAdapter.execute(pagamentoAtual);
        pagamentoProcessado.setStatus("APROVADO");

        salvarPagamentoAdapterPort.salvar(pagamentoProcessado);

        return pagamentoProcessado;
    }
}

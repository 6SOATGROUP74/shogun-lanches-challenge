package com.example.demo.core.usecase;

import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.domain.exception.PagamentoNotFoundException;
import com.example.demo.core.domain.exception.PedidoNotFoundException;
import com.example.demo.core.domain.StatusPagamento;
import com.example.demo.core.ports.inbound.pagamento.ValidarPagamentoPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.ListarPedidosUseCasePort;
import com.example.demo.core.ports.outbound.pagamento.BuscarPagamentoAdapterPort;
import com.example.demo.core.ports.outbound.pagamento.SalvarPagamentoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.AtualizarPedidoAdapterPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ValidarPagamentoPedidoUseCase implements ValidarPagamentoPedidoUseCasePort {

    private final ListarPedidosUseCasePort listarPedidosUseCasePort;
    private final BuscarPagamentoAdapterPort buscarPagamentoAdapterPort;
    private final SalvarPagamentoAdapterPort salvarPagamentoAdapterPort;
    private final AtualizarPedidoAdapterPort atualizarPedidoAdapterPort;

    public ValidarPagamentoPedidoUseCase(ListarPedidosUseCasePort listarPedidosUseCasePort, BuscarPagamentoAdapterPort buscarPagamentoAdapterPort, SalvarPagamentoAdapterPort salvarPagamentoAdapterPort, AtualizarPedidoAdapterPort atualizarPedidoAdapterPort) {
        this.listarPedidosUseCasePort = listarPedidosUseCasePort;
        this.buscarPagamentoAdapterPort = buscarPagamentoAdapterPort;
        this.salvarPagamentoAdapterPort = salvarPagamentoAdapterPort;
        this.atualizarPedidoAdapterPort = atualizarPedidoAdapterPort;
    }

    @Override
    public Pagamento execute(Pagamento pagamento) {
        Pedido pedido = listarPedidosUseCasePort.listarPorCodReferencia(pagamento.getPedido().getCodReferenciaPedido());
        Pagamento pagamentoAtualizado = buscarPagamentoAdapterPort.buscar(pedido.getIdPagamento());

        if(Objects.isNull(pedido)){
            throw new PedidoNotFoundException("Pedido nao localizado.");
        }

        if(Objects.isNull(pagamentoAtualizado)){
            throw new PagamentoNotFoundException("Pagamento nao localizado.");
        }

        pagamentoAtualizado.setPedido(pedido);
        pagamentoAtualizado.setValorTotal(BigDecimal.valueOf(pedido.getValorTotal()));
        pagamentoAtualizado.setCodPagamento(pagamento.getCodPagamento());

        pedido.setEtapa("EM_PREPARACAO");
        pagamentoAtualizado.setNumeroPedido(pedido.getNumeroPedido());
        pagamentoAtualizado.setPedido(pedido);
        pagamentoAtualizado.setCodPagamento(pagamento.getCodPagamento());
        pagamentoAtualizado.setStatus(StatusPagamento.APROVADO.name());
        pagamentoAtualizado.setDataPagamento(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        final var dadosPagamento = salvarPagamentoAdapterPort.salvar(pagamentoAtualizado);

        pedido.setIdPagamento(dadosPagamento.getIdPagamento());

        atualizarPedidoAdapterPort.execute(pedido);

        return pagamento;
    }
}

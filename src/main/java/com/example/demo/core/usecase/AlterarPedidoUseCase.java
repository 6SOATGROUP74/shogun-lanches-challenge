package com.example.demo.core.usecase;

import com.example.demo.core.domain.Pedido;
import com.example.demo.core.ports.inbound.pedido.AlterarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.BuscarPedidoUseCasePort;
import com.example.demo.core.ports.outbound.pedido.AtualizarPedidoAdapterPort;

public class AlterarPedidoUseCase implements AlterarPedidoUseCasePort {

    private final AtualizarPedidoAdapterPort atualizarPedidoAdapterPort;
    private final BuscarPedidoUseCasePort buscarPedidoUseCasePort;


    public AlterarPedidoUseCase(AtualizarPedidoAdapterPort atualizarPedidoAdapterPort, BuscarPedidoUseCasePort buscarPedidoUseCasePort) {
        this.atualizarPedidoAdapterPort = atualizarPedidoAdapterPort;this.buscarPedidoUseCasePort = buscarPedidoUseCasePort;
    }

    @Override
    public Pedido execute(Pedido pedido) {
        Pedido pedidoEncontrado = buscarPedidoUseCasePort.buscarPorId(pedido.getNumeroPedido());
        pedidoEncontrado.setEtapa(pedido.getEtapa());
        return atualizarPedidoAdapterPort.execute(pedidoEncontrado);
    }
}

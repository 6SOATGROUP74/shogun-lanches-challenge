package com.example.demo.core.usecase;

import com.example.demo.core.domain.Pedido;
import com.example.demo.core.ports.inbound.pedido.AlterarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.BuscarPedidoUseCasePort;
import com.example.demo.core.ports.outbound.pedido.SalvarPedidoAdapterPort;

public class AlterarPedidoUseCase implements AlterarPedidoUseCasePort {

    private final SalvarPedidoAdapterPort salvarPedidoAdapterPort;
    private final BuscarPedidoUseCasePort buscarPedidoUseCasePort;


    public AlterarPedidoUseCase(SalvarPedidoAdapterPort salvarPedidoAdapterPort, BuscarPedidoUseCasePort buscarPedidoUseCasePort) {
        this.salvarPedidoAdapterPort = salvarPedidoAdapterPort;
        this.buscarPedidoUseCasePort = buscarPedidoUseCasePort;
    }

    @Override
    public void execute(Pedido pedido) {
        Pedido pedidoEncontrado = buscarPedidoUseCasePort.buscarPorId(pedido.getNumeroPedido());
        pedidoEncontrado.setEtapa(pedido.getEtapa());
        salvarPedidoAdapterPort.execute(pedidoEncontrado);
    }
}

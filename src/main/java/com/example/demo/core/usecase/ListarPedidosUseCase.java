package com.example.demo.core.usecase;

import com.example.demo.core.domain.Pedido;
import com.example.demo.core.ports.inbound.pedido.ListarPedidosUseCasePort;
import com.example.demo.core.ports.outbound.pedido.ListarPedidosAdapterPort;

import java.util.List;

public class ListarPedidosUseCase implements ListarPedidosUseCasePort {

    private final ListarPedidosAdapterPort listarPedidosAdapterPort;

    public ListarPedidosUseCase(ListarPedidosAdapterPort listarPedidosAdapterPort) {
        this.listarPedidosAdapterPort = listarPedidosAdapterPort;
    }

    @Override
    public List<Pedido> execute(){
        return listarPedidosAdapterPort.listarTodosPedidos();
    }

    @Override
    public List<Pedido> listarOrdenados() {
        return listarPedidosAdapterPort.listarPedidosOrdenados();
    }

    @Override
    public Pedido listarPorCodReferencia(String codReferencia) {
        return listarPedidosAdapterPort.buscarPedidoPorCodReferencia(codReferencia);
    }
}

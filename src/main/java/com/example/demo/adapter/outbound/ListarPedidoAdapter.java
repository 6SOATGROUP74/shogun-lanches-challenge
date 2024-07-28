package com.example.demo.adapter.outbound;

import com.example.demo.adapter.outbound.repository.PedidoRepository;
import com.example.demo.adapter.outbound.repository.mapper.PedidoEntityMapper;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.ports.outbound.pedido.ListarPedidosAdapterPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarPedidoAdapter implements ListarPedidosAdapterPort {

    private PedidoRepository repository;

    @Autowired
    public ListarPedidoAdapter(PedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Pedido> listarTodosPedidos() {
        return PedidoEntityMapper.INSTANCE.mapFrom(repository.findAll());
    }

    @Override
    public List<Pedido> listarPedidosOrdenados() {
        return PedidoEntityMapper.INSTANCE.mapFrom(repository.ordenaPedidos());
    }

    @Override
    public Pedido buscarPedidoPorCodReferencia(String codReferencia) {
        return PedidoEntityMapper.INSTANCE.mapFrom(repository.findByCodReferenciaPedido(codReferencia));
    }
}

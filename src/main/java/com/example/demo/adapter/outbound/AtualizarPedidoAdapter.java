package com.example.demo.adapter.outbound;

import com.example.demo.adapter.outbound.repository.PedidoRepository;
import com.example.demo.adapter.outbound.repository.mapper.PedidoEntityMapper;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.ports.outbound.pedido.AtualizarPedidoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.SalvarPedidoAdapterPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AtualizarPedidoAdapter implements AtualizarPedidoAdapterPort {

    private final PedidoRepository repository;

    @Autowired
    public AtualizarPedidoAdapter(PedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(Pedido pedido) {
       repository.save(PedidoEntityMapper.INSTANCE.updateFrom(pedido));
    }
}

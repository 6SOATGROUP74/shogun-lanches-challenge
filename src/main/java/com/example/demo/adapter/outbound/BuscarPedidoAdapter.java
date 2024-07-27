package com.example.demo.adapter.outbound;

import com.example.demo.adapter.outbound.repository.PedidoRepository;
import com.example.demo.adapter.outbound.repository.mapper.PedidoEntityMapper;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.ports.outbound.pedido.BuscarPedidoAdapterPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BuscarPedidoAdapter implements BuscarPedidoAdapterPort {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido execute(Long idPedido) {
        return PedidoEntityMapper.INSTANCE.mapFrom(pedidoRepository.findById(idPedido).orElse(null));
    }
}

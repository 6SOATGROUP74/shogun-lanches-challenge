package com.example.demo.adapter.outbound;

import com.example.demo.adapter.outbound.repository.PedidoRepository;
import com.example.demo.adapter.outbound.repository.mapper.PedidoEntityMapper;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.ports.outbound.pedido.BuscarPedidoAdapterPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BuscarPedidoAdapter implements BuscarPedidoAdapterPort {

    private final PedidoRepository pedidoRepository;

    public BuscarPedidoAdapter(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido execute(Long idPedido) {
        logger.info("m=execute, msg=Consultando na base informações de pedido, pedidoId={}", idPedido);
        return PedidoEntityMapper.INSTANCE.mapFrom(pedidoRepository.findById(idPedido).orElse(null));
    }

    private Logger logger = LoggerFactory.getLogger(BuscarPedidoAdapter.class);
}

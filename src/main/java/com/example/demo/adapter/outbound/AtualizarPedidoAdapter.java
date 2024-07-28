package com.example.demo.adapter.outbound;

import com.example.demo.adapter.outbound.repository.PedidoRepository;
import com.example.demo.adapter.outbound.repository.mapper.PedidoEntityMapper;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.ports.outbound.pedido.AtualizarPedidoAdapterPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

@Component
public class AtualizarPedidoAdapter implements AtualizarPedidoAdapterPort {

    private final PedidoRepository repository;

    public AtualizarPedidoAdapter(PedidoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Pedido execute(Pedido pedido) {
        logger.info("m=execute, status=init, msg=Persistindo pedido na base de dados, pedido={}", pedido);
        Pedido pedidoPersistido = PedidoEntityMapper.INSTANCE.mapFrom(repository.save(PedidoEntityMapper.INSTANCE.updateFrom(pedido)));
        logger.info("m=execute, status=sucess, msg=Persistindo pedido na base de dados, pedido={}", pedido);
        return pedidoPersistido;
    }

    private Logger logger = LoggerFactory.getLogger(AtualizarPedidoAdapter.class);
}

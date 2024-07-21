package com.example.demo.core.usecase;

import com.example.demo.adapter.outbound.repository.PedidoRepository;
import com.example.demo.adapter.outbound.repository.entity.PedidoEntity;
import com.example.demo.adapter.outbound.repository.mapper.PedidoEntityMapper;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.ports.inbound.pedido.BuscarPedidoUseCasePort;

import java.util.Optional;

public class BuscarPedidoUseCase implements BuscarPedidoUseCasePort {

    private final PedidoRepository pedidoRepository;

    public BuscarPedidoUseCase(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Pedido buscarPorId(Long idPedido) {
        Optional<PedidoEntity> pedidoEntity = pedidoRepository.findById(idPedido);
        return PedidoEntityMapper.INSTANCE.mapFrom(pedidoEntity.get());
    }
}

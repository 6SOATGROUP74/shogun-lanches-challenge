package com.example.demo.core.ports.inbound.pedido;

import com.example.demo.core.domain.Pedido;

public interface BuscarPedidoUseCasePort {
    Pedido buscarPorId(Long id);
}

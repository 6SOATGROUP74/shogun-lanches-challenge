package com.example.demo.core.ports.outbound.pedido;

import com.example.demo.core.domain.Pedido;

public interface AtualizarPedidoAdapterPort {
    void execute(Pedido pedido);
}
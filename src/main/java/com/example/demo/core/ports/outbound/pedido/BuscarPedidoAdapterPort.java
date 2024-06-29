package com.example.demo.core.ports.outbound.pedido;

import com.example.demo.core.domain.Pedido;

public interface BuscarPedidoAdapterPort {

    Pedido execute(Long idPedido);
}

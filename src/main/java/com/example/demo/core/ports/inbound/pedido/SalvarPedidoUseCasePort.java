package com.example.demo.core.ports.inbound.pedido;

import com.example.demo.core.domain.Pedido;

import java.util.List;

public interface SalvarPedidoUseCasePort {
   Pedido execute(Pedido pedido);
}
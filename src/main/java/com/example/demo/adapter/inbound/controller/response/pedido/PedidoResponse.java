package com.example.demo.adapter.inbound.controller.response.pedido;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PedidoResponse {

    private String numeroPedido;
    private ClienteResponse cliente;
    private List<ComposicaoResponse> composicao;
    private String valorTotal;
    private String etapa;

}

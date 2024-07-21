package com.example.demo.adapter.inbound.controller.request.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({ "numeroPedido", "etapa"})
public class AtualizaPedidoRequest {

    @JsonProperty("numero_pedido")
    private Long numeroPedido;

    private String etapa;
}

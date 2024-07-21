package com.example.demo.adapter.inbound.controller.request.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class PedidoRequest {

    @JsonProperty("id_cliente")
    @NotNull(message = "O campo obrigatório")
    private Long idCliente;

    @NotNull(message = "O campo obrigatório")
    private List<ComposicaoRequest> composicao;
}

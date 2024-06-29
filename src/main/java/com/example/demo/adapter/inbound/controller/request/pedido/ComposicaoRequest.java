package com.example.demo.adapter.inbound.controller.request.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ComposicaoRequest {

    @NotNull(message = "O campo obrigatório")
    @JsonProperty("id_produto")
    private Long idProduto;

    @NotNull(message = "O campo obrigatório")
    private int quantidade;

}

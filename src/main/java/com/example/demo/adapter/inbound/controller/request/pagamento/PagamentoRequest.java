package com.example.demo.adapter.inbound.controller.request.pagamento;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoRequest {

    @NotEmpty(message = "O campo não pode ser vazio.")
    @NotNull(message = "O campo obrigatório")
    @JsonProperty("numero_pedido")
    private Long numeroPedido;

    @NotEmpty(message = "O campo não pode ser vazio.")
    @NotNull(message = "O campo obrigatório")
    @JsonProperty("tipo_do_pagamento")
    private String tipoDoPagamento;

}

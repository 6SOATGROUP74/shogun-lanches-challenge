package com.example.demo.adapter.outbound.integration.pagbank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Produto {

    @JsonProperty("name")
    private String nomeProduto;

    @JsonProperty("quantity")
    private Long quantidade;

    @JsonProperty("unit_amount")
    private Long valor;
}

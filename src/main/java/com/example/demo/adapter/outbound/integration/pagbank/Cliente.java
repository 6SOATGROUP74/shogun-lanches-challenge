package com.example.demo.adapter.outbound.integration.pagbank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cliente {
    @JsonProperty("name")
    private String nome;
    @JsonProperty("email")
    private String email;
    @JsonProperty("tax_id")
    private String documento;
}

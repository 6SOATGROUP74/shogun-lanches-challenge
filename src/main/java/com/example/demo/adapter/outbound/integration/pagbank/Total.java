package com.example.demo.adapter.outbound.integration.pagbank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Total {

    public Total() {
    }

    @JsonProperty("value")
    private Long valor;
}

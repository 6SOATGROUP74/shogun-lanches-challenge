package com.example.demo.adapter.outbound.integration.pagbank;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Total {

    @JsonProperty("value")
    private Long valor;
}

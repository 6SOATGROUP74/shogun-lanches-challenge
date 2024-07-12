package com.example.demo.adapter.outbound.integration.pagbank.request;

import com.example.demo.adapter.outbound.integration.pagbank.Total;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class QRCodeRequest {

    @JsonProperty("amount")
    private Total total;
}

package com.example.demo.adapter.outbound.integration.pagbank.request;

import com.example.demo.adapter.outbound.integration.pagbank.Total;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QRCodeRequest {

    @JsonProperty("amount")
    private Total total;
}

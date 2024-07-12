package com.example.demo.adapter.outbound.integration.pagbank.response;

import com.example.demo.adapter.outbound.integration.pagbank.LinkQRCode;
import com.example.demo.adapter.outbound.integration.pagbank.Total;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class QrCodeResponse {

    @JsonProperty("id")
    private String codigoQRCode;

    @JsonProperty("expiration_date")
    private String dataExpiracao;

    @JsonProperty("amount")
    private Total total;

    @JsonProperty("links")
    private List<LinkQRCode> links;
}
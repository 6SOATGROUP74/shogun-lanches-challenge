package com.example.demo.adapter.outbound.integration.pagbank.response;

import com.example.demo.adapter.outbound.integration.pagbank.Total;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagamento {

    @JsonProperty("id")
    private String codigoDoPagamento;

    @JsonProperty("reference_id")
    private String codigoReferenciaDoPedido;

    @JsonProperty("status")
    private String status;

    @JsonProperty("amount")
    private Total total;

    @JsonProperty("paid_at")
    private String dataPagamento;
}

package com.example.demo.adapter.outbound.integration.pagbank.response;

import com.example.demo.adapter.outbound.integration.pagbank.Cliente;
import com.example.demo.adapter.outbound.integration.pagbank.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class PagbankPagamentoResponse {

    @JsonProperty("id")
    private String codigoDoPagamento;

    @JsonProperty("reference_id")
    private String codigoReferenciaDoPedido;

    @JsonProperty("customer")
    private Cliente cliente;

    @JsonProperty("items")
    private List<Produto> produtos;

    @JsonProperty("qr_codes")
    private List<QrCodeResponse> qrCodes;
}

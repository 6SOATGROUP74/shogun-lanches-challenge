package com.example.demo.adapter.outbound.integration.pagbank.request;

import com.example.demo.adapter.outbound.integration.pagbank.Cliente;
import com.example.demo.adapter.outbound.integration.pagbank.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PagbankPagamentoRequest {

    @JsonProperty("reference_id")
    private String codigoReferenciaDoPedido;

    @JsonProperty("customer")
    private Cliente cliente;

    @JsonProperty("items")
    private List<Produto> produtos;

    @JsonProperty("qr_codes")
    private List<QRCodeRequest> qrCodes;

    @JsonProperty("notification_urls")
    private List<String> notificationUrls;
}

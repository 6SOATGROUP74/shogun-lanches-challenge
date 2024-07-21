package com.example.demo.adapter.outbound.integration.pagbank.request;


import com.example.demo.adapter.outbound.integration.pagbank.response.Pagamento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PagbankStatusPagamentoRequest {
    @JsonProperty("charges")
    private List<Pagamento> pagamentos;
}
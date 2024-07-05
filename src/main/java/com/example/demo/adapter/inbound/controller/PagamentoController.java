package com.example.demo.adapter.inbound.controller;

import com.example.demo.adapter.inbound.controller.request.pagamento.PagamentoRequest;
import com.example.demo.adapter.inbound.controller.request.pagamento.mapper.PagamentoMapper;
import com.example.demo.adapter.outbound.integration.pagbank.PagbankClient;
import com.example.demo.adapter.outbound.integration.pagbank.request.PagbankPagamentoRequest;
import com.example.demo.adapter.outbound.integration.pagbank.response.PagbankPagamentoResponse;
import com.example.demo.adapter.outbound.integration.pagbank.response.PagbankStatusPagamentoResponse;
import com.example.demo.core.ports.inbound.pagamento.PagarPedidoUseCasePort;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pagamento")
@AllArgsConstructor
public class PagamentoController {

    //TODO Ajustar código após testes

    @Autowired
    PagbankClient pagbankClient;

    @Value("${pagbank.token}")
    String token;

    public PagamentoController() {
    }

    public PagamentoController(String token) {
        this.token = token;
    }

    private PagarPedidoUseCasePort pagarPedidoUseCasePort;

    @PostMapping("/fake")
    public ResponseEntity<?> realizarPagamento(@RequestBody PagamentoRequest pagamentoRequest) {

        pagarPedidoUseCasePort.checkout(PagamentoMapper.INSTANCE.mapFrom(pagamentoRequest));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping
    public ResponseEntity<PagbankPagamentoResponse> realizaPagamentoPagbank(@RequestBody PagbankPagamentoRequest pagbankPagamentoRequest){

        var response = pagbankClient.realizaPagamentoQRCodePix(token, pagbankPagamentoRequest);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }

    @GetMapping
    public ResponseEntity<PagbankStatusPagamentoResponse> consultaStatusPagamento(String codigoDoPedido) {

        var response = pagbankClient.consultaStatusPagamento(token, codigoDoPedido);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}

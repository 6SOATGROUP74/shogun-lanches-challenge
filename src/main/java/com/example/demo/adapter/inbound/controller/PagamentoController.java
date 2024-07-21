package com.example.demo.adapter.inbound.controller;

import com.example.demo.adapter.inbound.controller.request.pagamento.PagamentoRequest;
import com.example.demo.adapter.inbound.controller.request.pagamento.mapper.PagamentoMapper;
import com.example.demo.adapter.outbound.integration.pagbank.PagbankClient;
import com.example.demo.adapter.outbound.integration.pagbank.request.PagbankPagamentoRequest;

import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.ports.inbound.pagamento.AlterarStatusPagamentoUseCasePort;
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

    @Autowired
    private PagarPedidoUseCasePort pagarPedidoUseCasePort;

    @Autowired
    private AlterarStatusPagamentoUseCasePort alterarStatusPagamentoUseCasePort;


    @Value("${pagbank.token}")
    String token;

    public PagamentoController() {
    }

    public PagamentoController(String token) {
        this.token = token;
    }

    @PostMapping
    public ResponseEntity<?> realizarPagamento(@RequestBody PagamentoRequest pagamentoRequest) {

        Pagamento pagamento = pagarPedidoUseCasePort.checkout(PagamentoMapper.INSTANCE.mapFrom(pagamentoRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }

    @GetMapping
    public ResponseEntity<Pagamento> consultaStatusPagamento(Long pagamentoId) {

        Pagamento pagamentoStatus = alterarStatusPagamentoUseCasePort.execute(pagamentoId);

        return ResponseEntity.ok(pagamentoStatus);
    }

    //TODO ajustar o webhook
    @PostMapping("/webhook")
    public ResponseEntity<?> consultaStatusPagamentoWebhook(@RequestBody PagbankPagamentoRequest pagbankPagamentoRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).body(pagbankPagamentoRequest);
    }
}

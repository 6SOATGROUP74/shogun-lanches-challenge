package com.example.demo.adapter.inbound.controller;

import com.example.demo.adapter.inbound.controller.request.pagamento.PagamentoRequest;
import com.example.demo.adapter.inbound.controller.request.pagamento.mapper.PagamentoMapper;
import com.example.demo.adapter.inbound.controller.response.pagamento.mapper.PagamentoResponseMapper;
import com.example.demo.adapter.outbound.integration.pagbank.request.PagbankWebhookRequest;
import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.ports.inbound.pagamento.PagarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pagamento.ValidarPagamentoPedidoUseCasePort;
import com.example.demo.core.ports.outbound.pagamento.BuscarPagamentoAdapterPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pagamento")
public class PagamentoController {

    private static final Logger logger = LoggerFactory.getLogger(PagamentoController.class);

    private final PagarPedidoUseCasePort pagarPedidoUseCasePort;
    private final ValidarPagamentoPedidoUseCasePort validarPagamentoPedidoUseCasePort;
    private final BuscarPagamentoAdapterPort buscarPagamentoAdapterPort;

    public PagamentoController(PagarPedidoUseCasePort pagarPedidoUseCasePort, ValidarPagamentoPedidoUseCasePort validarPagamentoPedidoUseCasePort, BuscarPagamentoAdapterPort buscarPagamentoAdapterPort){
        this.pagarPedidoUseCasePort = pagarPedidoUseCasePort;
        this.validarPagamentoPedidoUseCasePort = validarPagamentoPedidoUseCasePort;
        this.buscarPagamentoAdapterPort = buscarPagamentoAdapterPort;
    }
    
    @PostMapping
    public ResponseEntity<?> realizarPagamento(@RequestBody PagamentoRequest pagamentoRequest) {

        logger.info("m=realizarPagamento, status=init,  msg=Realiza processo de pagamento, pagamentoRequest={}", pagamentoRequest);

        Pagamento pagamento = pagarPedidoUseCasePort.checkout(PagamentoMapper.INSTANCE.mapFrom(pagamentoRequest));

        logger.info("m=realizarPagamento, status=success,  msg=Processo de pagamento realizado com sucesso, pagamentoRequest={}", pagamentoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(PagamentoMapper.INSTANCE.mapFrom(pagamento));
    }

    @GetMapping
    public ResponseEntity<?> consultaStatusPagamento(Long pagamentoId) {

        logger.info("m=consultaStatusPagamento, status=init,  msg=Consulta status de pagamento, pagamentoId={}", pagamentoId);

        Pagamento pagamentoStatus = buscarPagamentoAdapterPort.buscar(pagamentoId);

        logger.info("m=consultaStatusPagamento, status=sucess,  msg=Consulta status de pagamento realizada com sucesso, pagamentoId={}", pagamentoId);

        return ResponseEntity.ok().body(PagamentoResponseMapper.INSTANCE.mapFrom(pagamentoStatus));
    }

    @PostMapping("/webhook")
    public ResponseEntity<?> recebeConfirmacaoDePagamentoWebhook(@RequestBody PagbankWebhookRequest pagbankPagamentoRequest) {

        logger.info("m=recebeConfirmacaoDePagamentoWebhook, msg=Recebendo confirmação de status de pagamento do Pagbank, pagbankPagamentoRequest={}", pagbankPagamentoRequest);

        var pagamento = PagamentoMapper.INSTANCE.mapFrom(pagbankPagamentoRequest);

        validarPagamentoPedidoUseCasePort.execute(pagamento);

        logger.info("m=recebeConfirmacaoDePagamentoWebhook, msg=Confirmação de pagamento recebido do Pagbank com sucesso, pagbankPagamentoRequest={}", pagbankPagamentoRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(PagamentoMapper.INSTANCE.mapConvertFrom(pagamento));
    }
}

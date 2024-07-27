package com.example.demo.adapter.inbound.controller;

import com.example.demo.adapter.inbound.controller.request.pagamento.PagamentoRequest;
import com.example.demo.adapter.inbound.controller.request.pagamento.mapper.PagamentoMapper;
import com.example.demo.adapter.outbound.integration.pagbank.request.PagbankWebhookRequest;
import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.ports.inbound.pagamento.AlterarStatusPagamentoUseCasePort;
import com.example.demo.core.ports.inbound.pagamento.PagarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pagamento.ValidarPagamentoPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.ListarPedidosUseCasePort;
import com.example.demo.core.ports.outbound.pagamento.BuscarPagamentoAdapterPort;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private PagarPedidoUseCasePort pagarPedidoUseCasePort;

    @Autowired
    private AlterarStatusPagamentoUseCasePort alterarStatusPagamentoUseCasePort;

    @Autowired
    private ListarPedidosUseCasePort listarPedidosUseCasePort;

    @Autowired
    private ValidarPagamentoPedidoUseCasePort validarPagamentoPedidoUseCasePort;

    @Autowired
    private BuscarPagamentoAdapterPort buscarPagamentoAdapterPort;

    @Value("${pagbank.token}")
    String token;

    public PagamentoController() {
    }


    public PagamentoController(ListarPedidosUseCasePort listarPedidosUseCasePort) {
        this.listarPedidosUseCasePort = listarPedidosUseCasePort;
    }

    public PagamentoController(ListarPedidosUseCasePort listarPedidosUseCasePort, String token) {
        this.listarPedidosUseCasePort = listarPedidosUseCasePort;
        this.token = token;
    }

    // TODO exportar para o controler de pedido
    @PostMapping
    public ResponseEntity<?> realizarPagamento(@RequestBody PagamentoRequest pagamentoRequest) {

        Pagamento pagamento = pagarPedidoUseCasePort.checkout(PagamentoMapper.INSTANCE.mapFrom(pagamentoRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }

    //TODO trazer somente o status do pagamento
    @GetMapping("/fake")
    public ResponseEntity<Pagamento> consultaStatusPagamentoSemWebhook(Long pagamentoId) {

        Pagamento pagamentoStatus = alterarStatusPagamentoUseCasePort.execute(pagamentoId);

        return ResponseEntity.ok(pagamentoStatus);
    }

    @GetMapping
    public ResponseEntity<?> consultaStatusPagamento(Long pagamentoId) {

        Pagamento pagamentoStatus = buscarPagamentoAdapterPort.buscar(pagamentoId);

        return ResponseEntity.ok(pagamentoStatus.getStatus());
    }

    //TODO ajustar o webhook
    @PostMapping("/webhook")
    public ResponseEntity<?> consultaStatusPagamentoWebhook(@RequestBody PagbankWebhookRequest pagbankPagamentoRequest) {

        logger.info("m=consultaStatusPagamentoWebhook, msg=Recebendo confirmação de status de pagamento do Pagbank, pagbankPagamentoRequest={}", pagbankPagamentoRequest);

        var pagamento = PagamentoMapper.INSTANCE.mapFrom(pagbankPagamentoRequest);

        validarPagamentoPedidoUseCasePort.execute(pagamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(pagamento);
    }

    private Logger logger = LoggerFactory.getLogger(PagamentoController.class);
}

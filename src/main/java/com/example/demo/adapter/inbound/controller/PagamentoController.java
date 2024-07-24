package com.example.demo.adapter.inbound.controller;

import com.example.demo.adapter.inbound.controller.request.pagamento.PagamentoRequest;
import com.example.demo.adapter.inbound.controller.request.pagamento.mapper.PagamentoMapper;
import com.example.demo.adapter.outbound.integration.BuscarPagamentoAdapter;
import com.example.demo.adapter.outbound.integration.pagbank.PagbankClient;

import com.example.demo.adapter.outbound.integration.pagbank.request.PagbankWebhookRequest;
import com.example.demo.adapter.outbound.repository.mapper.PagamentoEntityMapper;
import com.example.demo.adapter.outbound.repository.mapper.PedidoEntityMapper;
import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.ports.inbound.pagamento.AlterarStatusPagamentoUseCasePort;
import com.example.demo.core.ports.inbound.pagamento.PagarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pagamento.ValidarPagamentoPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.ListarPedidosUseCasePort;
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
    PagbankClient pagbankClient;

    @Autowired
    private PagarPedidoUseCasePort pagarPedidoUseCasePort;

    @Autowired
    private AlterarStatusPagamentoUseCasePort alterarStatusPagamentoUseCasePort;

    @Autowired
    private ListarPedidosUseCasePort listarPedidosUseCasePort;

    @Autowired
    private ValidarPagamentoPedidoUseCasePort validarPagamentoPedidoUseCasePort;

    @Value("${pagbank.token}")
    String token;
    @Autowired
    private BuscarPagamentoAdapter buscarPagamentoAdapter;

    public PagamentoController() {
    }


    public PagamentoController(ListarPedidosUseCasePort listarPedidosUseCasePort) {
        this.listarPedidosUseCasePort = listarPedidosUseCasePort;
    }

    public PagamentoController(ListarPedidosUseCasePort listarPedidosUseCasePort, String token) {
        this.listarPedidosUseCasePort = listarPedidosUseCasePort;
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
    public ResponseEntity<?> consultaStatusPagamentoWebhook(@RequestBody PagbankWebhookRequest pagbankPagamentoRequest) {

        logger.info("m=consultaStatusPagamentoWebhook, msg=Recebendo confirmação de status de pagamento do Pagbank, pagbankPagamentoRequest={}", pagbankPagamentoRequest);

//        var pedido = listarPedidosUseCasePort.listarPorCodReferencia(pagbankPagamentoRequest.getPagamentos().get(0).getCodigoReferenciaDoPedido());
//        var pagamento = buscarPagamentoAdapter.buscar(pedido.getIdPagamento());
//        validarPagamentoPedidoUseCasePort.execute(pagamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(PagamentoMapper.INSTANCE.mapFrom(pagbankPagamentoRequest));
    }

    private Logger logger = LoggerFactory.getLogger(PagamentoController.class);
}

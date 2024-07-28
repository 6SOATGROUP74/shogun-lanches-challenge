package com.example.demo.adapter.inbound.controller;

import com.example.demo.adapter.inbound.controller.request.pedido.AtualizaPedidoRequest;
import com.example.demo.adapter.inbound.controller.request.pedido.PedidoRequest;
import com.example.demo.adapter.inbound.controller.request.pedido.mapper.PedidoMapper;
import com.example.demo.adapter.inbound.controller.response.pedido.mapper.PedidoResponseMapper;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.ports.inbound.pedido.AlterarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.CriarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.ListarPedidosUseCasePort;
import com.example.demo.core.ports.inbound.pedido.SalvarPedidoUseCasePort;
import com.example.demo.core.ports.outbound.pedido.BuscarPedidoAdapterPort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final ListarPedidosUseCasePort listarPedidosUseCasePort;
    private final CriarPedidoUseCasePort criarPedidoUseCasePort;
    private final AlterarPedidoUseCasePort alterarPedidoUseCasePort;

    private static final Logger logger = LogManager.getLogger(PedidoController.class);

    @GetMapping
    public ResponseEntity<?> listarPedidos() {
        logger.info("m=listarPedidos, msg=Lista pedidos");
        return ResponseEntity.ok()
                .body(PedidoMapper.INSTANCE.mapFrom(listarPedidosUseCasePort.listarOrdenados()));
    }

    @PostMapping
    public ResponseEntity<?> salvarPedido(@RequestBody @Valid PedidoRequest pedidoRequest) {

        logger.info("m=salvarPedido, status=init,  msg=Cria pedido, pedidoRequest={}", pedidoRequest);

        Pedido pedidoCriado = criarPedidoUseCasePort.criarPedido(PedidoMapper.INSTANCE.mapFrom(pedidoRequest));

        logger.info("m=salvarPedido, status=success,  msg=Pedido criado com sucesso, pedidoRequest={}", pedidoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(PedidoResponseMapper.INSTANCE.mapFrom(pedidoCriado));
    }

    @PatchMapping("/atualiza")
    public ResponseEntity<?> atualizaPedido(@RequestBody AtualizaPedidoRequest atualizaPedidoRequest) {
        logger.info("m=atualizaPedido, status=init,  msg=Atualiza pedido, atualizaPedidoRequest={}", atualizaPedidoRequest);
        Pedido pedidoAlterado = PedidoMapper.INSTANCE.mapFrom(atualizaPedidoRequest);

        Pedido pedido = alterarPedidoUseCasePort.execute(pedidoAlterado);

        logger.info("m=atualizaPedido, status=success,  msg=Pedido atualizado com sucesso, atualizaPedidoRequest={}", atualizaPedidoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(PedidoResponseMapper.INSTANCE.mapFrom(pedido));
    }
}

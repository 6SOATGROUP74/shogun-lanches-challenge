package com.example.demo.adapter.inbound.controller;

import com.example.demo.adapter.inbound.controller.request.pedido.AtualizaPedidoRequest;
import com.example.demo.adapter.inbound.controller.request.pedido.PedidoRequest;
import com.example.demo.adapter.inbound.controller.response.pedido.PedidoResponse;
import com.example.demo.adapter.inbound.controller.request.pedido.mapper.PedidoMapper;
import com.example.demo.adapter.inbound.controller.response.pedido.mapper.PedidoResponseMapper;
import com.example.demo.adapter.outbound.repository.PedidoRepository;
import com.example.demo.adapter.outbound.repository.entity.PedidoEntity;
import com.example.demo.adapter.outbound.repository.mapper.PedidoEntityMapper;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.ports.inbound.pedido.AlterarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.ListarPedidosUseCasePort;
import com.example.demo.core.ports.inbound.pedido.SalvarPedidoUseCasePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
@AllArgsConstructor
public class PedidoController {

    @Autowired
    private final PedidoRepository pedidoRepository;

    private final ListarPedidosUseCasePort listarPedidosUseCasePort;
    private final SalvarPedidoUseCasePort salvarPedidoUseCasePort;
    private final AlterarPedidoUseCasePort alterarPedidoUseCasePort;

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listarPedidos() {
        return ResponseEntity.ok()
                .body(PedidoResponseMapper.INSTANCE.mapFrom(
                        listarPedidosUseCasePort.execute()));
    }

    @PostMapping
    public ResponseEntity<?> salvarPedido(@RequestBody @Valid PedidoRequest request) {
        salvarPedidoUseCasePort.execute(PedidoMapper.INSTANCE.mapFrom(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/ordenado")
    public ResponseEntity<?> testePedidos() {

        List<PedidoEntity> pedidos = pedidoRepository.ordenaPedidos();

        return ResponseEntity.ok()
                .body(PedidoEntityMapper.INSTANCE.mapFrom(pedidos));
    }

    @PostMapping("/atualiza")
    public ResponseEntity<?> atualizaPedido(@RequestBody AtualizaPedidoRequest atualizaPedidoRequest) {

        //TODO Configurar os retornos
        Pedido pedidoAlterado = PedidoMapper.INSTANCE.mapFrom(atualizaPedidoRequest);

        alterarPedidoUseCasePort.execute(pedidoAlterado);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

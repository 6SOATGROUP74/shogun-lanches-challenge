package com.example.demo.adapter.inbound.controller;

import com.example.demo.adapter.inbound.controller.request.pedido.PedidoRequest;
import com.example.demo.adapter.inbound.controller.response.pedido.PedidoResponse;
import com.example.demo.adapter.inbound.controller.request.pedido.mapper.PedidoMapper;
import com.example.demo.adapter.inbound.controller.response.pedido.mapper.PedidoResponseMapper;
import com.example.demo.core.ports.inbound.pedido.ListarPedidosUseCasePort;
import com.example.demo.core.ports.inbound.pedido.SalvarPedidoUseCasePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/pedidos")
@AllArgsConstructor
public class PedidoController {

    private final ListarPedidosUseCasePort listarPedidosUseCasePort;
    private final SalvarPedidoUseCasePort salvarPedidoUseCasePort;

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
}

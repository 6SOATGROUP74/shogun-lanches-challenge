package com.example.demo.adapter.inbound.controller;

import com.example.demo.adapter.inbound.controller.request.produto.ProdutoRequest;
import com.example.demo.adapter.inbound.controller.request.pedido.mapper.ProdutoMapper;
import com.example.demo.adapter.inbound.controller.response.pedido.mapper.ProdutoResponseMapper;
import com.example.demo.adapter.inbound.controller.response.produto.ProdutoResponse;
import com.example.demo.core.ports.inbound.produto.GerenciarProdutoUseCasePort;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/produtos")
@AllArgsConstructor
public class ProdutoController {

    private final GerenciarProdutoUseCasePort gerenciarProdutoUseCasePort;

    @PostMapping
    public ResponseEntity<?> incluir(@RequestBody @Valid ProdutoRequest request){
        gerenciarProdutoUseCasePort.salvar(ProdutoMapper.INSTANCE.mapFrom(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{categoria}")
    public ResponseEntity<List<ProdutoResponse>> buscar(@PathVariable("categoria") final String categoria){

        return ResponseEntity.ok().body(
                ProdutoResponseMapper.INSTANCE.mapFrom(
                        gerenciarProdutoUseCasePort.buscarProdutoPorCategoria(categoria)));
    }

    @PatchMapping("/{idProduto}")
    public ResponseEntity<?> alterar(@RequestBody @Valid ProdutoRequest request,
                                     @PathVariable("idProduto") final Long idProduto){
        gerenciarProdutoUseCasePort.alterarProduto(ProdutoMapper.INSTANCE.mapFrom(request), idProduto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idProduto}")
    public ResponseEntity<?> deletar(@PathVariable("idProduto") final Long idProduto){
        gerenciarProdutoUseCasePort.deletarProduto(idProduto);
        return ResponseEntity.ok().build();
    }

}

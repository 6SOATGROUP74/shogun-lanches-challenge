package com.example.demo.core.ports.outbound.produto;

import com.example.demo.core.domain.Produto;

import java.util.List;

public interface GerenciarProdutoAdapterPort {
    void salvar(Produto produto);
    List<Produto> buscarProdutoPorCategoria(String categoria);
    Produto buscarProdutoPorId(Long id);
}

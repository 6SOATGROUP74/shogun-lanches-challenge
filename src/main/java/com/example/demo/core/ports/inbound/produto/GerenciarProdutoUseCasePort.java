package com.example.demo.core.ports.inbound.produto;

import com.example.demo.core.domain.Produto;

import java.util.List;

public interface GerenciarProdutoUseCasePort {
    void salvar(Produto produto);
    List<Produto> buscarProdutoPorCategoria(String categoria);
    void deletarProduto(Long idProduto);
    void alterarProduto(Produto produto, Long idProduto);
}

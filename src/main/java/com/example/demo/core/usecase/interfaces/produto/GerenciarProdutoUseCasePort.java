package com.example.demo.core.usecase.interfaces.produto;

import com.example.demo.core.domain.Produto;

import java.util.List;

public interface GerenciarProdutoUseCasePort {
    Produto salvar(Produto produto);
    List<Produto> buscarProdutoPorCategoria(String categoria);
    void deletarProduto(Long idProduto);
    Produto alterarProduto(Produto produto, Long idProduto);
}

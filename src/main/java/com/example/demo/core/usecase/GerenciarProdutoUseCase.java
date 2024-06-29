package com.example.demo.core.usecase;

import com.example.demo.core.domain.CategoriaEnum;
import com.example.demo.core.domain.Produto;
import com.example.demo.core.domain.exception.CategoriaInvalidaException;
import com.example.demo.core.domain.exception.ProdutoNotFoundException;
import com.example.demo.core.ports.inbound.produto.GerenciarProdutoUseCasePort;
import com.example.demo.core.ports.outbound.produto.GerenciarProdutoAdapterPort;

import java.util.List;
import java.util.Objects;

public class GerenciarProdutoUseCase implements GerenciarProdutoUseCasePort {

    private final GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort;

    public GerenciarProdutoUseCase(GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort) {
        this.gerenciarProdutoAdapterPort = gerenciarProdutoAdapterPort;
    }

    @Override
    public void salvar(Produto produto) {
        produto.setStatus(true);
        if(!CategoriaEnum.contains(produto.getCategoria().toUpperCase())){
            throw new CategoriaInvalidaException("A categoria invalida");
        }
        gerenciarProdutoAdapterPort.salvar(produto);
    }

    @Override
    public List<Produto> buscarProdutoPorCategoria(String categoria) {
        if(!CategoriaEnum.contains(categoria.toUpperCase())){
            throw new CategoriaInvalidaException("A categoria invalida");
        }
        return gerenciarProdutoAdapterPort.buscarProdutoPorCategoria(categoria);
    }

    @Override
    public void deletarProduto(final Long idProduto) {
        var produto = gerenciarProdutoAdapterPort.buscarProdutoPorId(idProduto);

        if(Objects.isNull(produto)){
            throw new ProdutoNotFoundException("Produto nao localizado na base.");
        }

        produto.setStatus(false);
        gerenciarProdutoAdapterPort.salvar(produto);
    }

    @Override
    public void alterarProduto(Produto produto, final Long idProduto) {
        var result = gerenciarProdutoAdapterPort.buscarProdutoPorId(idProduto);

        if(Objects.isNull(result)){
            throw new ProdutoNotFoundException("Produto nao localizado na base.");
        }
        produto.setStatus(true);
        produto.setIdProduto(result.getIdProduto());
        gerenciarProdutoAdapterPort.salvar(produto);
    }
}

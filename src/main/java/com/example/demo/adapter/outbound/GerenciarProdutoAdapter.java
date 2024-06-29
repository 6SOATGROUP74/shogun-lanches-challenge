package com.example.demo.adapter.outbound;

import com.example.demo.adapter.outbound.repository.ProdutoRepository;
import com.example.demo.adapter.outbound.repository.mapper.ProdutoEntityMapper;
import com.example.demo.core.domain.Produto;
import com.example.demo.core.ports.outbound.produto.GerenciarProdutoAdapterPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class GerenciarProdutoAdapter implements GerenciarProdutoAdapterPort {

    private final ProdutoRepository produtoRepository;

    @Override
    public void salvar(Produto produto) {
        var produtoEntity = ProdutoEntityMapper.INSTANCE.mapFrom(produto);
        produtoRepository.save(produtoEntity);
    }

    @Override
    public List<Produto> buscarProdutoPorCategoria(String categoria) {
        return ProdutoEntityMapper.INSTANCE.mapFrom(produtoRepository.findByCategoriaAndStatus(categoria,true));
    }


    @Override
    public Produto buscarProdutoPorId(Long id) {
        return ProdutoEntityMapper.INSTANCE.mapFrom(produtoRepository.findById(id).orElse(null));
    }
}

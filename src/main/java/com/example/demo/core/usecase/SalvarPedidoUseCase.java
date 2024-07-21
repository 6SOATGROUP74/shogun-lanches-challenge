package com.example.demo.core.usecase;

import com.example.demo.core.domain.Composicao;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.domain.exception.ProdutoNotFoundException;
import com.example.demo.core.ports.inbound.pedido.SalvarPedidoUseCasePort;
import com.example.demo.core.ports.outbound.pedido.SalvarPedidoAdapterPort;
import com.example.demo.core.ports.outbound.produto.GerenciarProdutoAdapterPort;

import java.util.Objects;
import java.util.UUID;

public class SalvarPedidoUseCase implements SalvarPedidoUseCasePort {

    private final SalvarPedidoAdapterPort salvarPedidoAdapterPort;
    private final GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort;

    public SalvarPedidoUseCase(SalvarPedidoAdapterPort salvarPedidoAdapterPort, GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort) {
        this.salvarPedidoAdapterPort = salvarPedidoAdapterPort;
        this.gerenciarProdutoAdapterPort = gerenciarProdutoAdapterPort;
    }

    @Override
    public void execute(Pedido pedido) {

        pedido.getComposicao().forEach(item -> {

            var auxProduto = gerenciarProdutoAdapterPort.buscarProdutoPorId(item.getIdProduto());
            if(Objects.isNull(auxProduto) || !auxProduto.isStatus()){
                throw new ProdutoNotFoundException("Produto nao localizado na base ou inativo");
            }
            item.setCategoria(auxProduto.getCategoria());
            item.setPrecoUnitario(auxProduto.getValor());
            item.setNomeProduto(auxProduto.getNome());
            item.setIdProduto(auxProduto.getIdProduto());
        });

        double valorTotal = 0.0;

        for(Composicao composicao : pedido.getComposicao()){
            valorTotal += composicao.getQuantidade() * composicao.getPrecoUnitario();
        }

        pedido.setCodReferenciaPedido(UUID.randomUUID().toString());
        pedido.setValorTotal(valorTotal);
        pedido.setEtapa("RECEBIDO");

        salvarPedidoAdapterPort.execute(pedido);
    }

}

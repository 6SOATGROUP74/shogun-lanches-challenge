package com.example.demo.core.usecase;

import com.example.demo.core.domain.Cliente;
import com.example.demo.core.domain.Composicao;
import com.example.demo.core.domain.Pedido;
import com.example.demo.core.domain.exception.ProdutoNotFoundException;
import com.example.demo.core.ports.inbound.pedido.CriarPedidoUseCasePort;
import com.example.demo.core.ports.outbound.cliente.RecuperarClienteAdapterPort;
import com.example.demo.core.ports.outbound.pedido.BuscarPedidoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.SalvarPedidoAdapterPort;
import com.example.demo.core.ports.outbound.produto.GerenciarProdutoAdapterPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.example.demo.core.domain.StatusPedido.RECEBIDO;

public class CriarPedidoUseCase implements CriarPedidoUseCasePort {

    private static final Logger logger = LogManager.getLogger(CriarPedidoUseCase.class);

    private final SalvarPedidoAdapterPort salvarPedidoAdapterPort;
    private final GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort;
    private final RecuperarClienteAdapterPort recuperarClienteAdapterPort;
    private final BuscarPedidoAdapterPort buscarPedidoAdapterPort;

    public CriarPedidoUseCase(SalvarPedidoAdapterPort salvarPedidoAdapterPort, GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort, RecuperarClienteAdapterPort recuperarClienteAdapterPort, BuscarPedidoAdapterPort buscarPedidoAdapterPort) {
        this.salvarPedidoAdapterPort = salvarPedidoAdapterPort;
        this.gerenciarProdutoAdapterPort = gerenciarProdutoAdapterPort;
        this.recuperarClienteAdapterPort = recuperarClienteAdapterPort;
        this.buscarPedidoAdapterPort = buscarPedidoAdapterPort;
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        logger.info("m=criarPedido, status=init, msg=Criando pedido={}", pedido);

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
        pedido.setEtapa(RECEBIDO.name());

        Cliente cliente = recuperarClienteAdapterPort.recuperarPorId(pedido.getCliente().getIdCliente());
        pedido.setCliente(cliente);
        Pedido pedidoCriado = salvarPedidoAdapterPort.execute(pedido);

        logger.info("m=criarPedido, status=success, msg=Pedido criado com sucesso, pedido={}", pedido);

        return buscarPedidoAdapterPort.execute(pedidoCriado.getNumeroPedido());
    }
}

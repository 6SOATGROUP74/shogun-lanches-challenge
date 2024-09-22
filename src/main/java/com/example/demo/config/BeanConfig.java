package com.example.demo.config;

import com.example.demo.adapter.gateway.interfaces.cliente.ClienteCognitoAdapterPort;
import com.example.demo.adapter.gateway.interfaces.cliente.IncluirClienteAdapterPort;
import com.example.demo.adapter.gateway.interfaces.cliente.RecuperarClienteAdapterPort;
import com.example.demo.adapter.gateway.interfaces.pagamento.BuscarPagamentoAdapterPort;
import com.example.demo.adapter.gateway.interfaces.pagamento.SalvarPagamentoAdapterPort;
import com.example.demo.adapter.gateway.interfaces.pedido.AtualizarPedidoAdapterPort;
import com.example.demo.adapter.gateway.interfaces.pedido.BuscarPedidoAdapterPort;
import com.example.demo.adapter.gateway.interfaces.pedido.ListarPedidosAdapterPort;
import com.example.demo.adapter.gateway.interfaces.pedido.SalvarPedidoAdapterPort;
import com.example.demo.adapter.gateway.interfaces.produto.GerenciarProdutoAdapterPort;
import com.example.demo.core.usecase.impl.AlterarPedidoUseCase;
import com.example.demo.core.usecase.impl.AlterarStatusPagamentoUseCase;
import com.example.demo.core.usecase.impl.BuscarPedidoUseCase;
import com.example.demo.core.usecase.impl.CriarPedidoUseCase;
import com.example.demo.core.usecase.impl.GerenciarProdutoUseCase;
import com.example.demo.core.usecase.impl.IncluirClienteUseCase;
import com.example.demo.core.usecase.impl.ListarPedidosUseCase;
import com.example.demo.core.usecase.impl.PagarPedidoUseCase;
import com.example.demo.core.usecase.impl.RecuperarClienteUseCase;
import com.example.demo.core.usecase.impl.SalvarPedidoUseCase;
import com.example.demo.core.usecase.impl.ValidarPagamentoPedidoUseCase;
import com.example.demo.core.usecase.interfaces.cliente.IncluirClienteUseCasePort;
import com.example.demo.core.usecase.interfaces.cliente.RecuperarClienteUseCasePort;
import com.example.demo.core.usecase.interfaces.pagamento.AlterarStatusPagamentoUseCasePort;
import com.example.demo.core.usecase.interfaces.pagamento.PagarPedidoUseCasePort;
import com.example.demo.core.usecase.interfaces.pagamento.ValidarPagamentoPedidoUseCasePort;
import com.example.demo.core.usecase.interfaces.pedido.AlterarPedidoUseCasePort;
import com.example.demo.core.usecase.interfaces.pedido.BuscarPedidoUseCasePort;
import com.example.demo.core.usecase.interfaces.pedido.CriarPedidoUseCasePort;
import com.example.demo.core.usecase.interfaces.pedido.ListarPedidosUseCasePort;
import com.example.demo.core.usecase.interfaces.pedido.SalvarPedidoUseCasePort;
import com.example.demo.core.usecase.interfaces.produto.GerenciarProdutoUseCasePort;
import com.example.demo.infrastructure.integration.pagbank.PagarPedidoPagbankAdapter;
import com.example.demo.infrastructure.integration.pagbank.ProcessaStatusPagamentoPagbankAdapter;
import com.example.demo.infrastructure.repository.PedidoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public IncluirClienteUseCasePort incluirClienteUseCasePort(IncluirClienteAdapterPort incluirClienteAdapterPort,
                                                               RecuperarClienteAdapterPort recuperarClienteAdapterPort,
                                                               ClienteCognitoAdapterPort clienteCognitoAdapterPort) {
        return new IncluirClienteUseCase(recuperarClienteAdapterPort, incluirClienteAdapterPort, clienteCognitoAdapterPort);
    }

    @Bean
    public RecuperarClienteUseCasePort recuperarClienteUseCasePort(RecuperarClienteAdapterPort recuperarClienteAdapterPort) {
        return new RecuperarClienteUseCase(recuperarClienteAdapterPort);
    }

    @Bean
    public ListarPedidosUseCasePort listarPedidosUseCasePort(ListarPedidosAdapterPort listarPedidosAdapterPort) {
        return new ListarPedidosUseCase(listarPedidosAdapterPort);
    }

    @Bean
    public SalvarPedidoUseCasePort salvarPedidoUseCasePort(SalvarPedidoAdapterPort salvarPedidoAdapterPort) {
        return new SalvarPedidoUseCase(salvarPedidoAdapterPort);
    }

    @Bean
    public GerenciarProdutoUseCasePort incluirProdutoUseCasePort(GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort) {
        return new GerenciarProdutoUseCase(gerenciarProdutoAdapterPort);
    }

    @Bean
    public PagarPedidoUseCasePort pagarPedidoUseCasePort(PagarPedidoPagbankAdapter pagarPedidoPagbankAdapter,
                                                         SalvarPagamentoAdapterPort salvarPagamentoAdapterPort,
                                                         BuscarPedidoAdapterPort buscarPedidoAdapterPort,
                                                         AtualizarPedidoAdapterPort atualizarPedidoAdapterPort) {
        return new PagarPedidoUseCase(pagarPedidoPagbankAdapter, salvarPagamentoAdapterPort, buscarPedidoAdapterPort, atualizarPedidoAdapterPort);
    }

    @Bean
    public AlterarStatusPagamentoUseCasePort alterarStatusPagamentoUseCasePort(ProcessaStatusPagamentoPagbankAdapter processaStatusPagamentoPagbankAdapter, BuscarPagamentoAdapterPort buscarPagamentoAdapterPort, SalvarPagamentoAdapterPort salvarPagamentoAdapterPort, BuscarPedidoAdapterPort buscarPedidoAdapterPort) {
        return new AlterarStatusPagamentoUseCase(processaStatusPagamentoPagbankAdapter, buscarPagamentoAdapterPort, salvarPagamentoAdapterPort, buscarPedidoAdapterPort);
    }

    @Bean
    public AlterarPedidoUseCasePort alterarPedidoUseCasePort(AtualizarPedidoAdapterPort atualizarPedidoAdapterPort, BuscarPedidoUseCasePort buscarPedidoUseCasePort){
        return new AlterarPedidoUseCase(atualizarPedidoAdapterPort, buscarPedidoUseCasePort);
    }

    @Bean
    public BuscarPedidoUseCasePort buscarPedidoUseCasePort(PedidoRepository pedidoRepository){
        return new BuscarPedidoUseCase(pedidoRepository);
    }

    @Bean
    public ValidarPagamentoPedidoUseCasePort validarPagamentoPedidoUseCasePort(ListarPedidosUseCasePort listarPedidosUseCasePort, SalvarPagamentoAdapterPort salvarPagamentoAdapterPort, AtualizarPedidoAdapterPort atualizarPedidoAdapterPort, BuscarPagamentoAdapterPort buscarPagamentoAdapterPort) {
        return new ValidarPagamentoPedidoUseCase(listarPedidosUseCasePort, buscarPagamentoAdapterPort, salvarPagamentoAdapterPort, atualizarPedidoAdapterPort);
    }

    @Bean
    public CriarPedidoUseCasePort criarPedidoUseCasePort(SalvarPedidoAdapterPort salvarPedidoAdapterPort, GerenciarProdutoAdapterPort gerenciarProdutoAdapterPort, RecuperarClienteAdapterPort recuperarClienteAdapterPort, BuscarPedidoAdapterPort buscarPedidoAdapterPort){
        return new CriarPedidoUseCase(salvarPedidoAdapterPort, gerenciarProdutoAdapterPort, recuperarClienteAdapterPort, buscarPedidoAdapterPort);
    }
}

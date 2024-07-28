package com.example.demo.config;

import com.example.demo.adapter.outbound.integration.pagbank.PagarPedidoPagbankAdapter;
import com.example.demo.adapter.outbound.integration.pagbank.ProcessaStatusPagamentoPagbankAdapter;
import com.example.demo.adapter.outbound.repository.PedidoRepository;
import com.example.demo.core.ports.inbound.cliente.IncluirClienteUseCasePort;
import com.example.demo.core.ports.inbound.cliente.RecuperarClienteUseCasePort;
import com.example.demo.core.ports.inbound.pagamento.AlterarStatusPagamentoUseCasePort;
import com.example.demo.core.ports.inbound.pagamento.PagarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pagamento.ValidarPagamentoPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.AlterarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.BuscarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.CriarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.pedido.ListarPedidosUseCasePort;
import com.example.demo.core.ports.inbound.pedido.SalvarPedidoUseCasePort;
import com.example.demo.core.ports.inbound.produto.GerenciarProdutoUseCasePort;
import com.example.demo.core.ports.outbound.cliente.IncluirClienteAdapterPort;
import com.example.demo.core.ports.outbound.cliente.RecuperarClienteAdapterPort;
import com.example.demo.core.ports.outbound.pagamento.BuscarPagamentoAdapterPort;
import com.example.demo.core.ports.outbound.pagamento.SalvarPagamentoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.AtualizarPedidoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.BuscarPedidoAdapterPort;
import com.example.demo.core.ports.outbound.pedido.ListarPedidosAdapterPort;
import com.example.demo.core.ports.outbound.pedido.SalvarPedidoAdapterPort;
import com.example.demo.core.ports.outbound.produto.GerenciarProdutoAdapterPort;
import com.example.demo.core.usecase.AlterarPedidoUseCase;
import com.example.demo.core.usecase.AlterarStatusPagamentoUseCase;
import com.example.demo.core.usecase.BuscarPedidoUseCase;
import com.example.demo.core.usecase.CriarPedidoUseCase;
import com.example.demo.core.usecase.GerenciarProdutoUseCase;
import com.example.demo.core.usecase.IncluirClienteUseCase;
import com.example.demo.core.usecase.ListarPedidosUseCase;
import com.example.demo.core.usecase.PagarPedidoUseCase;
import com.example.demo.core.usecase.RecuperarClienteUseCase;
import com.example.demo.core.usecase.SalvarPedidoUseCase;
import com.example.demo.core.usecase.ValidarPagamentoPedidoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public IncluirClienteUseCasePort incluirClienteUseCasePort(IncluirClienteAdapterPort incluirClienteAdapterPort,
                                                               RecuperarClienteAdapterPort recuperarClienteAdapterPort) {
        return new IncluirClienteUseCase(recuperarClienteAdapterPort, incluirClienteAdapterPort);
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

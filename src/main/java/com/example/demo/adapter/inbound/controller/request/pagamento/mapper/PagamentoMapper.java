package com.example.demo.adapter.inbound.controller.request.pagamento.mapper;

import com.example.demo.adapter.inbound.controller.request.pagamento.PagamentoRequest;
import com.example.demo.adapter.inbound.controller.request.pagamento.PagamentoResponse;
import com.example.demo.adapter.inbound.controller.request.pagamento.PagamentoStatusResponse;
import com.example.demo.adapter.outbound.integration.pagbank.request.PagbankWebhookRequest;
import com.example.demo.core.domain.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper
public interface PagamentoMapper {

    PagamentoMapper INSTANCE = Mappers.getMapper(PagamentoMapper.class);

    @Mapping(target = "pedido.codPedido", source = "codPedido")
    @Mapping(target = "codPagamento", expression = "java(pagbankWebhookRequest.getPagamentos().get(0).getCodigoDoPagamento())")
    @Mapping(target = "pedido.codReferenciaPedido", expression = "java(pagbankWebhookRequest.getPagamentos().get(0).getCodigoReferenciaDoPedido())")
    @Mapping(target = "status", expression = "java(pagbankWebhookRequest.getPagamentos().get(0).getStatus())")
    @Mapping(target = "valorTotal", expression = "java(converteParaBigDecimal(pagbankWebhookRequest.getPagamentos().get(0).getTotal().getValor()))")
    Pagamento mapFrom(PagbankWebhookRequest pagbankWebhookRequest);

    Pagamento mapFrom(PagamentoRequest pagamentoRequest);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "valorTotal", source = "valorTotal")
    @Mapping(target = "tipoDoPagamento", source = "tipoDoPagamento")
    @Mapping(target = "dataPagamento", source = "dataPagamento")
    @Mapping(target = "codPagamento", source = "codPagamento")
    @Mapping(target = "copiaCola", source = "copiaCola")
    @Mapping(target = "qrCodeLink", source = "qrCodeLink")
    @Mapping(target = "pedidoResponse.numeroPedido", source = "pedido.numeroPedido")
    @Mapping(target = "pedidoResponse.cliente.nome", source = "pedido.cliente.nome")
    @Mapping(target = "pedidoResponse.cliente.cpf", source = "pedido.cliente.cpf")
    @Mapping(target = "pedidoResponse.composicao", source = "pedido.composicao")
    @Mapping(target = "pedidoResponse.etapa", source = "pedido.etapa")
    @Mapping(target = "pedidoResponse.dataPedido", source = "pedido.dataPedido")
    @Mapping(target = "pedidoResponse.codPedido", source = "pedido.codPedido")
    @Mapping(target = "pedidoResponse.codReferenciaPedido", source = "pedido.codReferenciaPedido")
    PagamentoResponse mapFrom(Pagamento pagamento);

    @Mapping(target = "status", source = "status")
    @Mapping(target = "valorTotal", source = "valorTotal")
    @Mapping(target = "codPagamento", source = "codPagamento")
    PagamentoStatusResponse mapConvertFrom(Pagamento pagamento);

    @Named("converteParaBigDecimal")
    default BigDecimal converteParaBigDecimal(Long valor) {
        if (valor != null) {
            return BigDecimal.valueOf(valor);
        }
        return BigDecimal.ZERO;
    }
}


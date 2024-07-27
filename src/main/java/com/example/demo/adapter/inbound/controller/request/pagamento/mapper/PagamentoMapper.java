package com.example.demo.adapter.inbound.controller.request.pagamento.mapper;

import com.example.demo.adapter.inbound.controller.request.pagamento.PagamentoRequest;
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


    @Named("converteParaBigDecimal")
    default BigDecimal converteParaBigDecimal(Long valor) {
        if (valor != null) {
            return BigDecimal.valueOf(valor);
        }
        return BigDecimal.ZERO;
    }
}


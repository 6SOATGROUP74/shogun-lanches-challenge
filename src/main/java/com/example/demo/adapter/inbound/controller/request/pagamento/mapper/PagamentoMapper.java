package com.example.demo.adapter.inbound.controller.request.pagamento.mapper;

import com.example.demo.adapter.inbound.controller.request.pagamento.PagamentoRequest;
import com.example.demo.adapter.outbound.integration.pagbank.request.PagbankWebhookRequest;
import com.example.demo.core.domain.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PagamentoMapper {

    PagamentoMapper INSTANCE = Mappers.getMapper(PagamentoMapper.class);

    Pagamento mapFrom(PagamentoRequest pagamentoRequest);

    Pagamento mapFrom(PagbankWebhookRequest pagbankWebhookRequest);

}

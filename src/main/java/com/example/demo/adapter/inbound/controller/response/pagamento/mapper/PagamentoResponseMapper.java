package com.example.demo.adapter.inbound.controller.response.pagamento.mapper;

import com.example.demo.adapter.inbound.controller.response.pagamento.PagamentoResponse;
import com.example.demo.core.domain.Pagamento;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PagamentoResponseMapper {
    PagamentoResponseMapper INSTANCE = Mappers.getMapper(PagamentoResponseMapper.class);

    PagamentoResponse mapFrom(Pagamento pagamento);
}
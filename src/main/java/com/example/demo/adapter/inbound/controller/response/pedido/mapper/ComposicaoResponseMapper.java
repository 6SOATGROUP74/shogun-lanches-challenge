package com.example.demo.adapter.inbound.controller.response.pedido.mapper;

import com.example.demo.adapter.inbound.controller.response.pedido.ComposicaoResponse;
import com.example.demo.core.domain.Composicao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ComposicaoResponseMapper {

    ComposicaoResponseMapper INSTANCE = Mappers.getMapper(ComposicaoResponseMapper.class);

    ComposicaoResponse mapFrom(Composicao composicao);
    List<ComposicaoResponse> mapFrom(List<Composicao> composicao);

}

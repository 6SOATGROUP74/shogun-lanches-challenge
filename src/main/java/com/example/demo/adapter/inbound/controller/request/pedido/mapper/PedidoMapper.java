package com.example.demo.adapter.inbound.controller.request.pedido.mapper;

import com.example.demo.adapter.inbound.controller.request.pedido.PedidoRequest;
import com.example.demo.core.domain.Pedido;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(uses = ComposicaoMapper.class)
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);


    @Mapping(source = "idCliente", target = "cliente.idCliente")
    Pedido mapFrom(PedidoRequest pedidoRequest);

}

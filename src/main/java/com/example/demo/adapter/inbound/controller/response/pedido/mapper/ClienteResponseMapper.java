package com.example.demo.adapter.inbound.controller.response.pedido.mapper;

import com.example.demo.adapter.inbound.controller.response.pedido.ClienteResponse;
import com.example.demo.core.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteResponseMapper {

    ClienteResponseMapper INSTANCE = Mappers.getMapper(ClienteResponseMapper.class);

    ClienteResponse mapFrom(Cliente cliente);
}

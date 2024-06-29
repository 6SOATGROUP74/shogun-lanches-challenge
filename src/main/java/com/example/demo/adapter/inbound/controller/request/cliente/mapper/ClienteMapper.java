package com.example.demo.adapter.inbound.controller.request.cliente.mapper;

import com.example.demo.adapter.inbound.controller.request.cliente.ClienteRequest;
import com.example.demo.core.domain.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente mapFrom(ClienteRequest clienteRequest);
}

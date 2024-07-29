package com.example.demo.adapter.presenter.pedido;

import com.example.demo.adapter.controller.response.pedido.PedidoResponse;
import com.example.demo.core.domain.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface PedidoResponseMapper {

    PedidoResponseMapper INSTANCE = Mappers.getMapper(PedidoResponseMapper.class);

    PedidoResponse mapFrom(Pedido pedido);

    List<PedidoResponse> mapFrom(List<Pedido> pedido);
}

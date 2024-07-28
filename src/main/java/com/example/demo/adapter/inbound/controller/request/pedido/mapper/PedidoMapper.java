package com.example.demo.adapter.inbound.controller.request.pedido.mapper;

import com.example.demo.adapter.inbound.controller.request.pedido.AtualizaPedidoRequest;
import com.example.demo.adapter.inbound.controller.request.pedido.PedidoRequest;
import com.example.demo.adapter.outbound.repository.entity.ComposicaoEntity;
import com.example.demo.adapter.outbound.repository.entity.PedidoEntity;
import com.example.demo.core.domain.Composicao;
import com.example.demo.core.domain.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.List;


@Mapper(uses = ComposicaoMapper.class)
public interface PedidoMapper {

    PedidoMapper INSTANCE = Mappers.getMapper(PedidoMapper.class);

    @Mapping(source = "idCliente", target = "cliente.idCliente")
    @Mapping(source = "composicao", target = "composicao")
    Pedido mapFrom(PedidoRequest pedidoRequest);

    @Mapping(source = "numeroPedido", target = "numeroPedido")
    Pedido mapFrom(AtualizaPedidoRequest atualizaPedidoRequest);

    List<PedidoEntity> mapFrom(List<Pedido> pedidos);

    @Mapping(target = "numeroPedido", source = "numeroPedido")
    @Mapping(target = "cliente.idCliente", source = "cliente.idCliente")
    @Mapping(target = "cliente.nome", source = "cliente.nome")
    @Mapping(target = "cliente.cpf", source = "cliente.cpf")
    @Mapping(target = "cliente.email", source = "cliente.email")
    @Mapping(target = "valorTotal", source = "valorTotal")
    @Mapping(target = "etapa", source = "etapa")
    @Mapping(target = "idPagamento", source = "pagamentoEntity.idPagamento")
    @Mapping(target = "dataPedido", source = "dataPedido")
    @Mapping(target = "codPedido", source = "codPedido")
    @Mapping(target = "codReferenciaPedido", source = "codReferenciaPedido")
    @Mapping(target = "composicao", source = "composicao")
    Pedido mapFrom(PedidoEntity pedidoEntity);
}

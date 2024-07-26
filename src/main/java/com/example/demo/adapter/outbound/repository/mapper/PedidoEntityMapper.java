package com.example.demo.adapter.outbound.repository.mapper;

import com.example.demo.adapter.outbound.repository.entity.PedidoEntity;
import com.example.demo.core.domain.Pedido;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Mapper(uses = ComposicaoEntityMapper.class)
public interface PedidoEntityMapper {

    PedidoEntityMapper INSTANCE = Mappers.getMapper(PedidoEntityMapper.class);

    @Mapping(target = "dataPedido", expression = "java(dataHoraAtual())")
    PedidoEntity mapFrom(Pedido pedido);

    @Mapping(target =  "idPagamento", source = "pagamentoEntity.idPagamento")
    Pedido mapFrom(PedidoEntity pedidoEntity);
    List<Pedido> mapFrom(List<PedidoEntity> pedidoEntity);

    @Mapping(target = "dataMudancaEtapa", expression = "java(dataHoraAtual())")
    @Mapping(target =  "pagamentoEntity.idPagamento", source = "idPagamento")
    PedidoEntity updateFrom(Pedido pedido);

    default String dataHoraAtual() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @AfterMapping
    default PedidoEntity afterMapping(@MappingTarget PedidoEntity entity, Pedido pedido){
        entity.getComposicao().forEach(item -> {
            item.setPedido(entity);
        });
        entity.setCodReferenciaPedido(pedido.getCodReferenciaPedido());
        entity.setDataMudancaEtapa(dataHoraAtual());

       //TODO Causa do null pointer
        // entity.getPagamentoEntity().setIdPagamento(pedido.getIdPagamento());

        return entity;

    }

    Pedido mapFrom(Optional<PedidoEntity> pedidoEntity);
}

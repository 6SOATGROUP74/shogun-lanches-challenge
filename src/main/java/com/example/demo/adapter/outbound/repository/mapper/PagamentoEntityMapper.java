package com.example.demo.adapter.outbound.repository.mapper;

import com.example.demo.adapter.outbound.repository.entity.PagamentoEntity;
import com.example.demo.core.domain.Pagamento;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(imports = LocalDateTime.class)
public interface PagamentoEntityMapper {

    PagamentoEntityMapper INSTANCE = Mappers.getMapper(PagamentoEntityMapper.class);

    @Mapping(target = "dataPagamento", expression = "java(dataHoraAtual())")
    @Mapping(target = "pedidoEntity.numeroPedido", source = "numeroPedido")
    @Mapping(target = "pedidoEntity.etapa", source = "pedido.etapa")
    @Mapping(target = "pedidoEntity.dataMudancaEtapa", expression = "java(dataHoraAtual())")
    PagamentoEntity mapFrom(Pagamento pagamento);

    Pagamento mapFrom(PagamentoEntity pagamentoEntity);

    default String dataHoraAtual() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @AfterMapping
    default PagamentoEntity afterMapping(@MappingTarget PagamentoEntity target, Pagamento pagamento){
        target.getPedidoEntity().setPagamentoEntity(target);
        target.setCodPagamento(pagamento.getCodPagamento());
        return target;
    }
}

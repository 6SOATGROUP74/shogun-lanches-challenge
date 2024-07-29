package com.example.demo.infrastructure.repository.presenter;

import com.example.demo.core.domain.Pagamento;
import com.example.demo.infrastructure.repository.entity.PagamentoEntity;
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

    @Mapping(target = "copiaCola", source = "pagamentoEntity.copiaCola")
    @Mapping(target = "qrCodeLink", source = "pagamentoEntity.qrCodeLink")
    @Mapping(target = "dataPagamento", source = "dataPagamento")
    @Mapping(target = "idPagamento", source = "idPagamento")
    Pagamento mapFrom(PagamentoEntity pagamentoEntity);

    default String dataHoraAtual() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @AfterMapping
    default PagamentoEntity afterMapping(@MappingTarget PagamentoEntity target, Pagamento pagamento){
        target.getPedidoEntity().setPagamentoEntity(target);
        target.setCodPagamento(pagamento.getCodPagamento());
        target.setCopiaCola(pagamento.getCopiaCola());
        target.setQrCodeLink(pagamento.getQrCodeLink());
        target.setIdPagamento(pagamento.getIdPagamento());
        return target;
    }
}

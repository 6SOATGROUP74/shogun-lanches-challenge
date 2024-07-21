package com.example.demo.adapter.outbound.integration;

import com.example.demo.adapter.outbound.repository.PagamentoRepository;
import com.example.demo.adapter.outbound.repository.mapper.PagamentoEntityMapper;
import com.example.demo.adapter.outbound.repository.mapper.PedidoEntityMapper;
import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.ports.outbound.pagamento.BuscarPagamentoAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class BuscarPagamentoAdapter implements BuscarPagamentoAdapterPort {

    private final PagamentoRepository pagamentoRepository;

    public BuscarPagamentoAdapter(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public Pagamento buscar(Long pagamentoId) {
        // TODO Resolver problema de conversão
        //Problema para converter PedidoEntity
        final var pagamentoEntity = pagamentoRepository.findById(pagamentoId).orElse(null);
        final var pagamento = PagamentoEntityMapper.INSTANCE.mapFrom(pagamentoEntity);
        pagamento.setPedido(PedidoEntityMapper.INSTANCE.mapFrom(pagamentoEntity.getPedidoEntity()));
        pagamento.setNumeroPedido(pagamento.getPedido().getNumeroPedido());
        return pagamento;
    }
}


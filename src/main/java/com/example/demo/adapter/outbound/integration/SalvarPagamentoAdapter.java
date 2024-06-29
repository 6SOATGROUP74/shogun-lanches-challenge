package com.example.demo.adapter.outbound.integration;

import com.example.demo.adapter.outbound.repository.PagamentoRepository;
import com.example.demo.adapter.outbound.repository.entity.PagamentoEntity;
import com.example.demo.adapter.outbound.repository.mapper.PagamentoEntityMapper;
import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.ports.outbound.pagamento.SalvarPagamentoAdapterPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalvarPagamentoAdapter implements SalvarPagamentoAdapterPort {

    private final PagamentoRepository pagamentoRepository;

    @Autowired
    public SalvarPagamentoAdapter(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        final var result = pagamentoRepository.save(PagamentoEntityMapper.INSTANCE.mapFrom(pagamento));
        return PagamentoEntityMapper.INSTANCE.mapFrom(result);
    }
}

package com.example.demo.core.ports.inbound.pagamento;

import com.example.demo.core.domain.Pagamento;

public interface AlterarStatusPagamentoUseCasePort {
    void execute(Long pagamentoId);
}

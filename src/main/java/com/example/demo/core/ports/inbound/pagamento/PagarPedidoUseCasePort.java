package com.example.demo.core.ports.inbound.pagamento;

import com.example.demo.core.domain.Pagamento;

public interface PagarPedidoUseCasePort {
    void checkout(Pagamento pagamento);
}

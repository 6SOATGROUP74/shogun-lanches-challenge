package com.example.demo.core.ports.outbound.pagamento;

import com.example.demo.core.domain.Pagamento;

public interface PagarPedidoAdapterPort {
    Pagamento pagar(Pagamento pagamento);
}

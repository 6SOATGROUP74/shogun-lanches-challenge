package com.example.demo.adapter.outbound.integration;

import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.ports.outbound.pagamento.PagarPedidoAdapterPort;
import org.springframework.stereotype.Component;

@Component
public class PagarPedidoAdapter implements PagarPedidoAdapterPort {

    @Override
    public Pagamento pagar(Pagamento pagamento) {
        pagamento.setTipoDoPagamento("QR Code Mercado Pago");
        pagamento.setStatus("APROVADO");
        return pagamento;
    }
}

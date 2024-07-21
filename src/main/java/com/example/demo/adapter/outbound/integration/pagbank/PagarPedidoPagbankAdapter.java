package com.example.demo.adapter.outbound.integration.pagbank;

import com.example.demo.adapter.outbound.integration.pagbank.request.PagbankPagamentoRequest;
import com.example.demo.adapter.outbound.integration.pagbank.request.QRCodeRequest;
import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.ports.outbound.pagamento.PagarPedidoAdapterPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Arrays;

@Component
public class PagarPedidoPagbankAdapter implements PagarPedidoAdapterPort{

    @Value("${pagbank.token}")
    String token;

    @Autowired
    private PagbankClient pagbankClient;

    public PagarPedidoPagbankAdapter(PagbankClient pagbankClient) {
        this.pagbankClient = pagbankClient;
    }

    @Override
    public Pagamento pagar(Pagamento pagamento) {
        var response = pagbankClient.realizaPagamentoQRCodePix(token, criaRequestParaPagamento(pagamento));
        pagamento.getPedido().setCodPedido(response.getBody().getCodigoDoPagamento());
        pagamento.setTipoDoPagamento("QR Code PagBank");
        pagamento.setStatus("PENDENTE");
        pagamento.setCopiaCola(response.getBody().getQrCodes().get(0).getCopiaCola());
        pagamento.setQrCodeLink(response.getBody().getQrCodes().get(0).getLinks().get(0).getLinkDoQRCode());
        return pagamento;
    }

    private PagbankPagamentoRequest criaRequestParaPagamento(Pagamento pagamento) {
        PagbankPagamentoRequest pagbankPagamentoRequest = new PagbankPagamentoRequest(
                pagamento.getPedido().getCodReferenciaPedido(),
                new Cliente("Shogun Lanches", "shogunlanches@gmail.com", "78026897000110"),
                Arrays.asList(new Produto("Ordem de pedido Shogun Lanches", 1L, pagamento.getValorTotal().longValue())),
                Arrays.asList(new QRCodeRequest(new Total(pagamento.getValorTotal().longValue()))),
                Arrays.asList("https://meusite.com/webhook")
        );
        return pagbankPagamentoRequest;
    }
}

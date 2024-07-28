package com.example.demo.adapter.outbound.integration.pagbank;

import com.example.demo.adapter.outbound.integration.pagbank.request.PagbankPagamentoRequest;
import com.example.demo.adapter.outbound.integration.pagbank.request.QRCodeRequest;
import com.example.demo.core.domain.Pagamento;
import com.example.demo.core.ports.outbound.pagamento.PagarPedidoAdapterPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Arrays;

import static com.example.demo.core.domain.FormasPagamentoEnum.QR_CODE_PAGBANK;
import static com.example.demo.core.domain.StatusPagamento.PENDENTE;

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
        logger.info("m=pagar, status=init, msg=Realizando requisição para geração de QR no PagBank, pagamento={}", pagamento);
        var response = pagbankClient.realizaPagamentoQRCodePix(token, criaRequestParaPagamento(pagamento));
        pagamento.getPedido().setCodPedido(response.getBody().getCodigoDoPagamento());
        pagamento.setTipoDoPagamento(QR_CODE_PAGBANK.name());
        pagamento.setStatus(PENDENTE.name());
        pagamento.setCopiaCola(response.getBody().getQrCodes().get(0).getCopiaCola());
        pagamento.setQrCodeLink(response.getBody().getQrCodes().get(0).getLinks().get(0).getLinkDoQRCode());
        logger.info("m=pagar, status=success, msg=Geração de QR no PagBank realizada com sucesso, pagamento={}", pagamento);
        return pagamento;
    }

    private PagbankPagamentoRequest criaRequestParaPagamento(Pagamento pagamento) {
        logger.info("m=criaRequestParaPagamento, status=init, msg=Criando a request para o PagBank, pagamento={}", pagamento);
        PagbankPagamentoRequest pagbankPagamentoRequest = new PagbankPagamentoRequest(
                pagamento.getPedido().getCodReferenciaPedido(),
                new Cliente("Shogun Lanches", "shogunlanches@gmail.com", "78026897000110"),
                Arrays.asList(new Produto("Ordem de pedido Shogun Lanches", 1L, pagamento.getValorTotal().longValue())),
                Arrays.asList(new QRCodeRequest(new Total(pagamento.getValorTotal().longValue()))),
                // TODO COLOCAR A URL DA AWS
                Arrays.asList("https://webhook.site/9ee42903-160b-4707-8c01-a5ebab9a92ef")
        );
        logger.info("m=criaRequestParaPagamento, status=success, msg=Request para o PagBank criado com sucesso, pagamento={}", pagamento);
        return pagbankPagamentoRequest;
    }

    private Logger logger = LoggerFactory.getLogger(PagarPedidoPagbankAdapter.class);
}

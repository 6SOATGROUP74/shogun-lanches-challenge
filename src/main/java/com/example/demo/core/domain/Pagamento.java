package com.example.demo.core.domain;

import java.math.BigDecimal;

public class Pagamento {

    private Long idPagamento;
    private Long numeroPedido;
    private String status;
    private BigDecimal valorTotal;
    private String tipoDoPagamento;
    private Pedido pedido;
    private String codPagamento;

    public String getCodPagamento() {
        return codPagamento;
    }

    public void setCodPagamento(String codPagamento) {
        this.codPagamento = codPagamento;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getTipoDoPagamento() {
        return tipoDoPagamento;
    }

    public void setTipoDoPagamento(String tipoDoPagamento) {
        this.tipoDoPagamento = tipoDoPagamento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}

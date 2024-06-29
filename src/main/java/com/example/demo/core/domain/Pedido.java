package com.example.demo.core.domain;

import java.util.List;

public class Pedido {

    private Long numeroPedido;
    private Cliente cliente;
    private Double valorTotal;
    private List<Composicao> composicao;
    private String status;
    private String etapa;
    private Long idPagamento;
    private String dataPedido;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(Long numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public List<Composicao> getComposicao() {
        return composicao;
    }

    public void setComposicao(List<Composicao> composicao) {
        this.composicao = composicao;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }

    public Long getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }
}

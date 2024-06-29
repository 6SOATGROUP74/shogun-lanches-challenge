package com.example.demo.adapter.outbound.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_pedido")
public class PedidoEntity {

    @Id
    @Column(name = "numero_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numeroPedido;

    @Column(name = "data_pedido")
    private String dataPedido;

    @Column(name = "valor_total")
    private Double valorTotal;

    @Column(name = "etapa")
    private String etapa;

    @Column(name = "data_mudanca_etapa")
    private String dataMudancaEtapa;

    @OneToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComposicaoEntity> composicao;

    @OneToOne
    @JoinColumn(name = "id_pagamento")
    private PagamentoEntity pagamentoEntity;

}

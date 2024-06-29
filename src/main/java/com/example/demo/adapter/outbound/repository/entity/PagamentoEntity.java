package com.example.demo.adapter.outbound.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "tb_pagamento")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long idPagamento;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_pedido")
    private PedidoEntity pedidoEntity;

    @Column(name = "status")
    private String status;

    @Column(name = "valor_total")
    private BigDecimal valorTotal;

    @Column(name = "tipo_do_pagamento")
    private String tipoDoPagamento;

    @Column(name = "data_pagamento")
    private String dataPagamento;

}

package com.example.demo.adapter.outbound.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private String idCliente;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data_cadastro")
    private String dataCadastro;

    @Column(name = "email")
    private String email;

    @Column(name = "cpf")
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private List<PedidoEntity> pedido;

}

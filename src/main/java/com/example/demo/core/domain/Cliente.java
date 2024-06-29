package com.example.demo.core.domain;

public class Cliente {

    private Long idCliente;
    private String nome;
    private String cpf;
    private String email;

    public Cliente(String nome, Long idCliente, String cpf, String email) {
        this.nome = nome;
        this.idCliente = idCliente;
        this.cpf = cpf;
        this.email = email;
    }

    public String getCpf() {
        if(cpf != null) {
            return cpf.replaceAll("\\D", "");
        }
        return null;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

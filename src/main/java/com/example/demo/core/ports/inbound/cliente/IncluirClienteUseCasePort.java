package com.example.demo.core.ports.inbound.cliente;

import com.example.demo.core.domain.Cliente;

public interface IncluirClienteUseCasePort {
    Cliente execute(Cliente cliente);
}

package com.example.demo.core.ports.outbound.cliente;

import com.example.demo.core.domain.Cliente;

public interface IncluirClienteAdapterPort {
    void execute(Cliente cliente);
}

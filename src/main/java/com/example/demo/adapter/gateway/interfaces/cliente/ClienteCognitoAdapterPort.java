package com.example.demo.adapter.gateway.interfaces.cliente;

import com.example.demo.core.domain.Cliente;

public interface ClienteCognitoAdapterPort {

    boolean contains(Cliente cliente);
    boolean incluir(Cliente cliente);

}

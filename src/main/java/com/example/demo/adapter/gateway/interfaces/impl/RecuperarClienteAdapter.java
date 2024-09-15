package com.example.demo.adapter.gateway.interfaces.impl;


import com.example.demo.core.domain.Cliente;
import com.example.demo.adapter.gateway.interfaces.cliente.RecuperarClienteAdapterPort;
import com.example.demo.infrastructure.cognito.CognitoUser;
import com.example.demo.infrastructure.repository.ClienteRepository;
import com.example.demo.infrastructure.repository.presenter.ClienteEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class RecuperarClienteAdapter implements RecuperarClienteAdapterPort {

    private final ClienteRepository clienteRepository;
    private final CognitoUser cognitoUser;

    @Override
    public Cliente execute(String documentoCliente) {
        return ClienteEntityMapper.INSTANCE.mapFrom(clienteRepository.findByCpf(documentoCliente));
    }

    @Override
    public Cliente recuperarPorId(Long clientId) {
        return ClienteEntityMapper.INSTANCE.mapFrom(clienteRepository.findById(clientId).get());
    }

    @Override
    public boolean consulta(Cliente cliente) {
        final var response = cognitoUser.getAdminUser(cliente.getCpf());
        return Objects.nonNull(response);
    }
}

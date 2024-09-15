package com.example.demo.adapter.gateway.interfaces.impl;

import com.example.demo.core.domain.Cliente;
import com.example.demo.adapter.gateway.interfaces.cliente.IncluirClienteAdapterPort;
import com.example.demo.infrastructure.cognito.CognitoUser;
import com.example.demo.infrastructure.repository.ClienteRepository;
import com.example.demo.infrastructure.repository.presenter.ClienteEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class IncluirClienteAdapter implements IncluirClienteAdapterPort {

    private final ClienteRepository clienteRepository;
    private final CognitoUser cognitoUser;

    @Override
    public Cliente execute(Cliente cliente) {
        return ClienteEntityMapper.INSTANCE.mapFrom(clienteRepository.save(ClienteEntityMapper.INSTANCE.mapFrom(cliente)));
    }

    @Override
    public boolean consulta(Cliente cliente){
        final var response = cognitoUser.getAdminUser(cliente.getCpf());
        return Objects.nonNull(response);
    }
}

package com.example.demo.core.usecase.impl;

import com.example.demo.adapter.gateway.interfaces.cliente.ClienteCognitoAdapterPort;
import com.example.demo.adapter.gateway.interfaces.impl.ClienteCognitoAdapter;
import com.example.demo.core.domain.Cliente;
import com.example.demo.exceptions.ClienteDuplicadoException;
import com.example.demo.core.usecase.interfaces.cliente.IncluirClienteUseCasePort;
import com.example.demo.adapter.gateway.interfaces.cliente.IncluirClienteAdapterPort;
import com.example.demo.adapter.gateway.interfaces.cliente.RecuperarClienteAdapterPort;

import java.util.Objects;

public class IncluirClienteUseCase implements IncluirClienteUseCasePort {

    private final IncluirClienteAdapterPort incluirClienteAdapterPort;
    private final RecuperarClienteAdapterPort recuperarClienteAdapterPort;
    private final ClienteCognitoAdapterPort clienteCognitoAdapterPort;


    public IncluirClienteUseCase(RecuperarClienteAdapterPort recuperarClienteAdapterPort,
                                 IncluirClienteAdapterPort incluirClienteAdapterPort, ClienteCognitoAdapterPort clienteCognitoAdapterPort) {
        this.recuperarClienteAdapterPort = recuperarClienteAdapterPort;
        this.incluirClienteAdapterPort = incluirClienteAdapterPort;
        this.clienteCognitoAdapterPort = clienteCognitoAdapterPort;
    }

    @Override
    public Cliente execute(Cliente cliente) {

        final var result = recuperarClienteAdapterPort.execute(cliente.getCpf());
        final var cognitoResponse = clienteCognitoAdapterPort.contains(cliente);

        if(Objects.nonNull(result) && cognitoResponse){
            throw new ClienteDuplicadoException("Cliente já existe na base de dados e cognito.");
        }

        final var responseIncluirCognito = clienteCognitoAdapterPort.incluir(cliente);

        return incluirClienteAdapterPort.execute(cliente);
    }
}

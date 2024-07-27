package com.example.demo.core.usecase;

import com.example.demo.core.domain.Cliente;
import com.example.demo.core.domain.exception.ClienteNotFoundException;
import com.example.demo.core.ports.inbound.cliente.RecuperarClienteUseCasePort;
import com.example.demo.core.ports.outbound.cliente.RecuperarClienteAdapterPort;

import java.util.Objects;

public class RecuperarClienteUseCase implements RecuperarClienteUseCasePort {

    private final RecuperarClienteAdapterPort recuperarClienteAdapterPort;

    public RecuperarClienteUseCase(RecuperarClienteAdapterPort recuperarClienteAdapterPort) {
        this.recuperarClienteAdapterPort = recuperarClienteAdapterPort;
    }

    @Override
    public Cliente execute(String documentoCliente) {
        final var auxDocumento = documentoCliente.replaceAll("\\D", "");
        final var result = recuperarClienteAdapterPort.execute(documentoCliente);

        if(Objects.isNull(result)){
            throw new ClienteNotFoundException("Cliente não localizado na base de dados.");
        }

        return result;
    }
}

package com.example.demo.core.usecase;

import com.example.demo.core.domain.Cliente;
import com.example.demo.core.domain.exception.ClienteDuplicadoException;
import com.example.demo.core.ports.inbound.cliente.IncluirClienteUseCasePort;
import com.example.demo.core.ports.outbound.cliente.IncluirClienteAdapterPort;
import com.example.demo.core.ports.outbound.cliente.RecuperarClienteAdapterPort;
import lombok.AllArgsConstructor;

import java.util.Objects;

public class IncluirClienteUseCase implements IncluirClienteUseCasePort {

    private final IncluirClienteAdapterPort incluirClienteAdapterPort;
    private final RecuperarClienteAdapterPort recuperarClienteAdapterPort;

    public IncluirClienteUseCase(RecuperarClienteAdapterPort recuperarClienteAdapterPort,
                                 IncluirClienteAdapterPort incluirClienteAdapterPort) {
        this.recuperarClienteAdapterPort = recuperarClienteAdapterPort;
        this.incluirClienteAdapterPort = incluirClienteAdapterPort;
    }

    @Override
    public void execute(Cliente cliente) {

        final var result = recuperarClienteAdapterPort.execute(cliente.getCpf());

        if(Objects.nonNull(result)){
            throw new ClienteDuplicadoException("Cliente já existe na base de dados.");
        }

        incluirClienteAdapterPort.execute(cliente);
    }
}

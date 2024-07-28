package com.example.demo.core.usecase;

import com.example.demo.core.domain.Cliente;
import com.example.demo.core.domain.exception.ClienteNotFoundException;
import com.example.demo.core.ports.inbound.cliente.RecuperarClienteUseCasePort;
import com.example.demo.core.ports.outbound.cliente.RecuperarClienteAdapterPort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class RecuperarClienteUseCase implements RecuperarClienteUseCasePort {

    private static final Logger logger = LogManager.getLogger(RecuperarClienteUseCase.class);

    private final RecuperarClienteAdapterPort recuperarClienteAdapterPort;

    public RecuperarClienteUseCase(RecuperarClienteAdapterPort recuperarClienteAdapterPort) {
        this.recuperarClienteAdapterPort = recuperarClienteAdapterPort;
    }

    @Override
    public Cliente execute(String documentoCliente) {
        logger.info("m=execute, status=init,  msg=Recuperando cliente por documento, documentoCliente={}", documentoCliente);
        final var auxDocumento = documentoCliente.replaceAll("\\D", "");
        final var result = recuperarClienteAdapterPort.execute(documentoCliente);

        if(Objects.isNull(result)){
            logger.error("m=execute, status=failure,  msg=Cliente não encontrado pelo documento informado, documentoCliente={}", documentoCliente);
            throw new ClienteNotFoundException("Cliente não localizado na base de dados.");
        }
        return result;
    }

    public Cliente recuperarPorId(Long clienteId) throws ClienteNotFoundException {
        logger.info("m=recuperarPorId, msg=Recuperando cliente por clienteId, clienteId={}", clienteId);
        return recuperarClienteAdapterPort.recuperarPorId(clienteId);
    }
    
}

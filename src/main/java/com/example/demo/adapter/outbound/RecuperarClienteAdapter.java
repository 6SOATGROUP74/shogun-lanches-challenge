package com.example.demo.adapter.outbound;

import com.example.demo.adapter.outbound.repository.ClienteRepository;
import com.example.demo.adapter.outbound.repository.mapper.ClienteEntityMapper;
import com.example.demo.core.domain.Cliente;
import com.example.demo.core.ports.outbound.cliente.RecuperarClienteAdapterPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RecuperarClienteAdapter implements RecuperarClienteAdapterPort {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente execute(String documentoCliente) {
        return ClienteEntityMapper.INSTANCE.mapFrom(clienteRepository.findByCpf(documentoCliente));
    }

    @Override
    public Cliente recuperarPorId(Long clientId) {
        return ClienteEntityMapper.INSTANCE.mapFrom(clienteRepository.findById(clientId).get());
    }
}

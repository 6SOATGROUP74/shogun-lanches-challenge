package com.example.demo.adapter.gateway.interfaces.impl;

import com.example.demo.adapter.gateway.interfaces.cliente.ClienteCognitoAdapterPort;
import com.example.demo.core.domain.Cliente;
import com.example.demo.infrastructure.cognito.CognitoClient;
import lombok.AllArgsConstructor;
import org.apache.catalina.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@AllArgsConstructor
public class ClienteCognitoAdapter implements ClienteCognitoAdapterPort {

    private final CognitoClient cognitoClient;

    @Override
    public boolean contains(Cliente cliente){
        final var response = cognitoClient.getAdminUser(cliente.getCpf());
        return Objects.nonNull(response);
    }

    @Override
    public boolean incluir(Cliente cliente) {
        final var response = cognitoClient.createUser(cliente);
        return Objects.nonNull(response) && StringUtils.isNotBlank(response);
    }

}

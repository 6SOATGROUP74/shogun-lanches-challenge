package com.example.demo.adapter.gateway.interfaces.impl;

import com.example.demo.core.domain.Cliente;
import com.example.demo.infrastructure.cognito.CognitoClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminGetUserResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class ClienteCognitoAdapterTest {

    @Mock
    CognitoClient client;

    @InjectMocks
    ClienteCognitoAdapter clienteCognitoAdapter;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contains_DeveRetornarTrue(){

        when(client.getAdminUser(eq(buildCliente().getCpf()))).thenReturn(AdminGetUserResponse.builder().build());
        assertTrue(clienteCognitoAdapter.contains(buildCliente()));
    }

    @Test
    void contains_DeveRetornarFalse(){
        when(client.getAdminUser(eq(buildCliente().getCpf()))).thenReturn(null);
        assertFalse(clienteCognitoAdapter.contains(buildCliente()));
    }

    @Test
    void incluir_DeveRetornarTrue(){
        when(client.createUser(any(Cliente.class))).thenReturn("4827448384");
        assertFalse(clienteCognitoAdapter.incluir(any(Cliente.class)));
    }

    @Test
    void incluir_DeveRetornarFalse(){
        when(client.createUser(any(Cliente.class))).thenReturn(null);
        assertFalse(clienteCognitoAdapter.incluir(any(Cliente.class)));
    }

    @Test
    void incluir_DeveRetornarFalseStringVazia(){
        when(client.createUser(any(Cliente.class))).thenReturn("");
        assertFalse(clienteCognitoAdapter.incluir(any(Cliente.class)));
    }

    Cliente buildCliente() {
        Cliente cliente = new Cliente();

        cliente.setCpf("CPF");
        cliente.setNome("Igu");
        cliente.setEmail("email@email.com");
        cliente.setIdCliente(1L);
        return cliente;
    }

}
package com.example.demo.infrastructure.cognito;

import com.example.demo.core.domain.Cliente;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;

@Component
@AllArgsConstructor
public class CognitoUser {

    private final CognitoIdentityProviderClient client;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CognitoProperties properties;

    public AdminGetUserResponse getAdminUser(final String username) {

        AdminGetUserRequest getUserRequest = AdminGetUserRequest.builder()
                .userPoolId(properties.getUserPoolId())
                .username(username)
                .build();

        logger.info("m=getAdminUser, status=init, msg=Montando payload AdminGetUser={}", getUserRequest);

        try {
            final var response = client.adminGetUser(getUserRequest);

        logger.info("m=getAdminUser, status=success, msg=Response AdminGetUser={}", response);

        return response;

        } catch (CognitoIdentityProviderException e) {
            logger.info("m=getAdminUser, status=error, msg=Erro ao consultar cognito AdminGetUser={}", e.awsErrorDetails().errorMessage());
            throw e;
        }
    }

    public String createUser(final Cliente cliente){

        AdminCreateUserRequest createUserRequest = AdminCreateUserRequest.builder()
                .userPoolId(properties.getUserPoolId())
                .username(cliente.getCpf())
                .userAttributes(
                        AttributeType.builder().name("email").value(cliente.getEmail()).build(),
                        AttributeType.builder().name("email_verified").value("true").build()
                )
                .build();

        AdminCreateUserResponse response = client.adminCreateUser(createUserRequest);

        return response.user().username();

    }
}

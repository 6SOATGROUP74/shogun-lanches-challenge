package com.example.demo.infrastructure.cognito;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "cognito")
public class CognitoProperties {

    private String region;
    private String userPoolId;

}

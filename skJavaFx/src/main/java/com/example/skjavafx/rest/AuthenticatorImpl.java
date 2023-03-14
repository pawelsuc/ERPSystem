package com.example.skjavafx.rest;

import com.example.skjavafx.dto.OperatorAuthenticationResultDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.example.skjavafx.dto.OperatorCredentialsDto;

public class AuthenticatorImpl implements Authenticator {

    private static final String AUTHENTICATION_URL = "http://localhost:8080/verify_operator_credentials";
    private final RestTemplate restTemplate;

    public AuthenticatorImpl() {
        restTemplate = new RestTemplate();
    }



    @Override
    public void authenticate(OperatorCredentialsDto operatorCredentialsDto, AuthenticationResultHandler authenticationResultHandler) {
        Runnable authenticationTask = () ->  {processAuthentication(operatorCredentialsDto,
                authenticationResultHandler);};
        Thread authenticationThread = new Thread(authenticationTask);

        authenticationThread.setDaemon(true);
        authenticationThread.start();


    }

    private void processAuthentication(OperatorCredentialsDto operatorCredentialsDto,
                                              AuthenticationResultHandler authenticationResultHandler) {
//        ResponseEntity<OperatorAuthenticationResultDto> responseEntity = restTemplate.postForEntity(AUTHENTICATION_URL, operatorCredentialsDto,
//                OperatorAuthenticationResultDto.class);
        OperatorAuthenticationResultDto dto = new OperatorAuthenticationResultDto();
        dto.setAuthenticated(true);
        dto.setFirstName("Paweł");
        dto.setLastName("Suchań");
        dto.setIdOperator(1L);

        authenticationResultHandler.handle(dto);

        };
    }


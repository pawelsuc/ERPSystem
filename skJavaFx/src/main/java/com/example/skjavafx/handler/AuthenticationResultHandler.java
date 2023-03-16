package com.example.skjavafx.handler;


import com.example.skjavafx.dto.OperatorAuthenticationResultDto;

@FunctionalInterface
public interface AuthenticationResultHandler {
    void handle(OperatorAuthenticationResultDto operatorAuthenticationResultDto);
}

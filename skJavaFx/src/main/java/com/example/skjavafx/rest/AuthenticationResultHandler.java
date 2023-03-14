package com.example.skjavafx.rest;


import com.example.skjavafx.dto.OperatorAuthenticationResultDto;

@FunctionalInterface
public interface AuthenticationResultHandler {
    void handle(OperatorAuthenticationResultDto operatorAuthenticationResultDto);
}

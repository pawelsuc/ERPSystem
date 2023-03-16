package com.example.skjavafx.rest;

import com.example.skjavafx.dto.OperatorCredentialsDto;
import com.example.skjavafx.handler.AuthenticationResultHandler;

public interface Authenticator {
    void authenticate(OperatorCredentialsDto operatorCredentialsDto, AuthenticationResultHandler authenticationResultHandler);
}

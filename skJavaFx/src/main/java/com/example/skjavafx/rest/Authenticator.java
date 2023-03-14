package com.example.skjavafx.rest;

import com.example.skjavafx.dto.OperatorCredentialsDto;

public interface Authenticator {
    void authenticate(OperatorCredentialsDto operatorCredentialsDto, AuthenticationResultHandler authenticationResultHandler);
}

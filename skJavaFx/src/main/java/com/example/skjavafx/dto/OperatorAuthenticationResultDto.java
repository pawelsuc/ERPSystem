package com.example.skjavafx.dto;

import lombok.Data;

@Data
public class OperatorAuthenticationResultDto {
    private Long idOperator;
    private String firstName;
    private String lastName;
    private boolean authenticated;
}

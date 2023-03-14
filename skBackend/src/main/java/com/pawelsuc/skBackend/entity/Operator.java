package com.pawelsuc.skBackend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Operator {
    @Id
    @GeneratedValue
    private Long idOperator;

    @Column
    private String login;

    @Column
    private String password;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "idEmployee")
    private Employee employee;
}

package com.proyecto2.gestion_pedidos.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class Customer {
    private Long id;
    private String documentNumber;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Boolean active;
}

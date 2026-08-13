package com.proyecto2.gestion_pedidos.core.usecase.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerResponse {

    private Long id;
    private String documentNumber;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Boolean active;
}

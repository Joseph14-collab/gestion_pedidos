package com.proyecto2.gestion_pedidos.core.usecase.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long customerId;
}

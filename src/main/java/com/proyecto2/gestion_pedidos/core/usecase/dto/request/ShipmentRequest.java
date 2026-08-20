package com.proyecto2.gestion_pedidos.core.usecase.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShipmentRequest {

    @NotNull(message = "El ID de la orden es obligatorio")
    private Long orderId;

    @NotBlank(message = "La dirección del envio es obligatoria")
    private String shippingAddress;

    @NotBlank(message = "Debe especificar la agencia o transportista(ej: Olva, Shalom)")
    private String carrier;

}

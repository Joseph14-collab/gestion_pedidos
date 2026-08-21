package com.proyecto2.gestion_pedidos.core.usecase.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    @NotNull(message = "El ID de la orden es obligatorio")
    private Long orderId;

    @NotBlank(message = "Debe especificar la pasarela de pago (ej. YAPE, PLIN, TRANSFERENCIA_BANCARIA, TARJETA)")
    private String paymentGateway;

    @NotNull(message = "El monto a pagar es obligatorio")
    private BigDecimal amount;

}

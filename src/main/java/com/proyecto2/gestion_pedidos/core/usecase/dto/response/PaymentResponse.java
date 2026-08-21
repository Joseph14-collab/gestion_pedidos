package com.proyecto2.gestion_pedidos.core.usecase.dto.response;

import com.proyecto2.gestion_pedidos.core.entity.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {
    private Long id;
    private Long orderId;
    private String paymentGateway;
    private String gatewayTransaction;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private PaymentStatus status;
}

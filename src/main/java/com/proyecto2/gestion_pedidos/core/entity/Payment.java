package com.proyecto2.gestion_pedidos.core.entity;

import com.proyecto2.gestion_pedidos.core.entity.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class Payment {
    private Long id;
    private Long orderId;
    private String paymentGateway;
    private String gatewayTransaction;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private PaymentStatus status;
}

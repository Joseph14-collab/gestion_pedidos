package com.proyecto2.gestion_pedidos.infrastructure.persistence.entity;

import com.proyecto2.gestion_pedidos.core.entity.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "payment_gateway", nullable = false, length = 50)
    private String paymentGateway;

    @Column(name = "gateway_transaction", unique = true, length = 100)
    private String gatewayTransaction;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_date", nullable = false, updatable = false)
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private PaymentStatus status;
}

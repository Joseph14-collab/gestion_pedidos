package com.proyecto2.gestion_pedidos.core.usecase.port.out.payment;

import com.proyecto2.gestion_pedidos.core.entity.Payment;
import com.proyecto2.gestion_pedidos.core.entity.enums.PaymentStatus;

import java.util.List;
import java.util.Optional;

public interface PaymentRepositoryPort {
    Payment save(Payment payment);
    Optional<Payment> findById(Long id);
    Optional<Payment> findByOrderId(Long orderId);
    List<Payment> findAll();
    List<Payment> findByStatus(PaymentStatus status);
}

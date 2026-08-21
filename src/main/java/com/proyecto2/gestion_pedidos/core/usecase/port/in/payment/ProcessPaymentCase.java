package com.proyecto2.gestion_pedidos.core.usecase.port.in.payment;

import com.proyecto2.gestion_pedidos.core.entity.Payment;

public interface ProcessPaymentCase {
    Payment createPendingPayment(Long orderId, String paymentGateway);
    Payment processPaymentResult(Long paymentId, boolean isSuccess);
}

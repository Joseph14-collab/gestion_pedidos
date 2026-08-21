package com.proyecto2.gestion_pedidos.core.usecase.port.in.payment;

import com.proyecto2.gestion_pedidos.core.entity.Payment;

public interface GetPaymentCase {
    Payment getPayment(Long id);
    Payment getPaymentByOrderId(Long orderId);
}

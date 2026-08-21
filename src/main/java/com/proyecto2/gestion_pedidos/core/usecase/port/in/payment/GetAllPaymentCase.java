package com.proyecto2.gestion_pedidos.core.usecase.port.in.payment;

import com.proyecto2.gestion_pedidos.core.entity.Payment;
import com.proyecto2.gestion_pedidos.core.entity.enums.PaymentStatus;

import java.util.List;

public interface GetAllPaymentCase {
    List<Payment> getAllPayments();
    List<Payment> getPaymentsByStatus(PaymentStatus status);
}
